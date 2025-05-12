package com.cambiazo.product.application.internal.queryservices;

import com.cambiazo.product.client.UserClient;
import com.cambiazo.product.domain.model.dtos.FavoriteProductDto;
import com.cambiazo.product.domain.model.dtos.Location;
import com.cambiazo.product.domain.model.dtos.ProductDto;
import com.cambiazo.product.domain.model.dtos.UserDto;
import com.cambiazo.product.domain.model.entities.*;
import com.cambiazo.product.domain.model.queries.GetAllFavoriteProductsByUserIdQuery;
import com.cambiazo.product.domain.services.IFavoriteProductQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FavoriteProductQueryServiceImpl implements IFavoriteProductQueryService {

    private final IFavoriteProductRepository favoriteProductRepository;
    private final IProductRepository productRepository;

    @Autowired
    private UserClient userClient;

//    private final UserRepository userRepository;
    private final IProductCategoryRepository productCategoryRepository;
    private final ICountryRepository countryRepository;
    private final IDepartmentRepository departmentRepository;
    private final IDistrictRepository districtRepository;

    private Map<Long, ProductCategory> productCategoryCache;
    private Map<Long, Country> countryCache;
    private Map<Long, Department> departmentCache;
    private Map<Long, District> districtCache;

    public FavoriteProductQueryServiceImpl(
            IFavoriteProductRepository favoriteProductRepository,
            IProductRepository productRepository,
            ICountryRepository countryRepository,
            IDepartmentRepository departmentRepository,
            IDistrictRepository districtRepository,
            IProductCategoryRepository productCategoryRepository
    ) {
        this.favoriteProductRepository = favoriteProductRepository;
//        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.countryRepository = countryRepository;
        this.departmentRepository = departmentRepository;
        this.districtRepository = districtRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @PostConstruct
    public void loadStaticDataIntoCache() {
        this.productCategoryCache = productCategoryRepository.findAll().stream()
                .collect(Collectors.toMap(ProductCategory::getId, category -> category));
        this.countryCache = countryRepository.findAll().stream()
                .collect(Collectors.toMap(Country::getId, country -> country));
        this.departmentCache = departmentRepository.findAll().stream()
                .collect(Collectors.toMap(Department::getId, department -> department));
        this.districtCache = districtRepository.findAll().stream()
                .collect(Collectors.toMap(District::getId, district -> district));
    }

    @Override
    public List<FavoriteProductDto> handle(GetAllFavoriteProductsByUserIdQuery query) {
//        User user = this.userRepository.findById(query.userId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserDto userResource = userClient.getUserById(query.userId()).getBody();
        if(userResource==null){
            throw new IllegalArgumentException("User not found");
        }

        List<FavoriteProduct> favoritesProducts = this.favoriteProductRepository.findFavoriteProductsByUserId(userResource.getId());

        if (favoritesProducts.isEmpty()) {
            return Collections.emptyList();
        }


        return favoritesProducts.stream().map(favoriteProduct -> {
            Product product = this.productRepository.findById(favoriteProduct.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            UserDto userResource2 = userClient.getUserById(product.getUserId()).getBody();

            ProductCategory productCategory = this.productCategoryCache.get(product.getProductCategoryId());
            if (productCategory == null) {
                throw new IllegalArgumentException("Product Category not found");
            }

            District district = this.districtCache.get(product.getDistrictId());
            if (district == null) {
                throw new IllegalArgumentException("District not found");
            }

            Department department = this.departmentCache.get(district.getDepartmentId());
            if (department == null) {
                throw new IllegalArgumentException("Department not found");
            }

            Country country = this.countryCache.get(department.getCountryId());
            if (country == null) {
                throw new IllegalArgumentException("Country not found");
            }

            Location location = new Location(
                    country.getId(), country.getName(),
                    department.getId(), department.getName(),
                    district.getId(), district.getName()
            );
            ProductDto productDto = new ProductDto(product, userResource2, productCategory, location);

            return new FavoriteProductDto(favoriteProduct.getId(), productDto, favoriteProduct.getUserId());
        }).toList();
    }
}
