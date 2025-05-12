package com.cambiazo.product.application.internal.queryservices;

import com.cambiazo.product.client.UserClient;
import com.cambiazo.product.domain.model.dtos.Location;
import com.cambiazo.product.domain.model.dtos.ProductDto;
import com.cambiazo.product.domain.model.dtos.UserDto;
import com.cambiazo.product.domain.model.entities.*;
import com.cambiazo.product.domain.model.queries.GetAllProductsByProductCategoryIdQuery;
import com.cambiazo.product.domain.model.queries.GetAllProductsByUserIdQuery;
import com.cambiazo.product.domain.model.queries.GetAllProductsQuery;
import com.cambiazo.product.domain.model.queries.GetProductByIdQuery;
import com.cambiazo.product.domain.services.IProductQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.*;
import feign.FeignException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ProductQueryServiceImpl implements IProductQueryService {

    private final IProductRepository productRepository;


    private final IProductCategoryRepository productCategoryRepository;

    private final IDistrictRepository districtRepository;

    private final IDepartmentRepository departmentRepository;

    private final ICountryRepository countryRepository;
    private final List<District>districts;
    private final List<Department>departments;

    private final List<Country>countries;

    private final List<ProductCategory>categories;

    @Autowired
    private UserClient userClient;

    public ProductQueryServiceImpl(
            IProductRepository productRepository,
            IProductCategoryRepository productCategoryRepository,
            IDistrictRepository districtRepository,
            IDepartmentRepository departmentRepository,
            ICountryRepository countryRepository
    ){
        this.productRepository=productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.districtRepository = districtRepository;
        this.departmentRepository = departmentRepository;
        this.countryRepository = countryRepository;
        this.districts = districtRepository.findAll();
        this.departments = departmentRepository.findAll();
        this.categories = productCategoryRepository.findAll();
        this.countries = countryRepository.findAll();
    }


    @Override
    public Optional<ProductDto> handle(GetProductByIdQuery query) {



        Product product = productRepository.findById(query.id())
                .orElseThrow(() -> new IllegalArgumentException("Product with id "+query.id()+" not found"));
        District district = districts.stream().filter(d -> d.getId().equals(product.getDistrictId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("District not found"));
        Department department = departments.stream().filter(d -> d.getId().equals(district.getDepartmentId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Country country = countries.stream().filter(c -> c.getId().equals(department.getCountryId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found"));
        ProductCategory productCategory = categories.stream().filter(c -> c.getId().equals(product.getProductCategoryId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product Category not found"));
        Location location = new Location(district.getId(),district.getName(), department.getId() ,department.getName(), country.getId(), country.getName());

        UserDto userResource = userClient.getUserById(product.getUserId()).getBody();

//        User user = userRepository.findById(product.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        var userResource = UserResource2FromEntityAssembler.toResourceFromEntity(user);
        return Optional.of(
                new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getDesiredObject(),
                        product.getPrice(),
                        product.getImage(),
                        product.getBoost(),
                        product.getAvailable(),
                        userResource,
                        productCategory,
                        location,
                        product.getCreatedAt()
                )
        );
    }

    @Override
    public List<ProductDto> handle(GetAllProductsQuery query) {
        List<Product>products = productRepository.findAll();
        List<UserDto>users = userClient.getAllUsers().getBody();
        if(users==null){
            throw new IllegalArgumentException("Error retrieving users");
        }
        return products.stream().map(product -> {
            District district = districts.stream().filter(d -> d.getId().equals(product.getDistrictId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("District not found"));
            Department department = departments.stream().filter(d -> d.getId().equals(district.getDepartmentId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            Country country = countries.stream().filter(c -> c.getId().equals(department.getCountryId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Country not found"));
            ProductCategory productCategory = categories.stream().filter(c -> c.getId().equals(product.getProductCategoryId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product Category not found"));
            Location location = new Location(district.getId(),district.getName(), department.getId() ,department.getName(), country.getId(), country.getName());
            UserDto userResource = users.stream().filter(u -> u.getId().equals(product.getUserId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getDesiredObject(),
                    product.getPrice(),
                    product.getImage(),
                    product.getBoost(),
                    product.getAvailable(),
                    userResource,
                    productCategory,
                    location,
                    product.getCreatedAt()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> handle(GetAllProductsByUserIdQuery query) {
        UserDto user = userClient.getUserById(query.userId()).getBody();
        if(user==null){
            throw new IllegalArgumentException("User not found");
        }

        List<Product> products = productRepository.findProductsByUserId(user.getId());
        return products.stream().map(product -> {
            District district = districts.stream().filter(d -> d.getId().equals(product.getDistrictId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("District not found"));
            Department department = departments.stream().filter(d -> d.getId().equals(district.getDepartmentId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            Country country = countries.stream().filter(c -> c.getId().equals(department.getCountryId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Country not found"));
            ProductCategory productCategory = categories.stream().filter(c -> c.getId().equals(product.getProductCategoryId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product Category not found"));
            Location location = new Location(district.getId(),district.getName(), department.getId() ,department.getName(), country.getId(), country.getName());
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getDesiredObject(),
                    product.getPrice(),
                    product.getImage(),
                    product.getBoost(),
                    product.getAvailable(),
                    user,
                    productCategory,
                    location,
                    product.getCreatedAt()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> handle(GetAllProductsByProductCategoryIdQuery query) {
        ProductCategory productCategory = productCategoryRepository.findById(query.productCategoryId())
                .orElseThrow(()->new IllegalArgumentException("Product Category with id "+query.productCategoryId()+" not found"));
        List<UserDto>users = userClient.getAllUsers().getBody();
        if(users==null){
            throw new IllegalArgumentException("Error retrieving users");
        }

        List<Product> products = productRepository.findProductsByProductCategoryId(productCategory);
        return products.stream().map(product -> {
            District district = districts.stream().filter(d -> d.getId().equals(product.getDistrictId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("District not found"));
            Department department = departments.stream().filter(d -> d.getId().equals(district.getDepartmentId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            Country country = countries.stream().filter(c -> c.getId().equals(department.getCountryId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Country not found"));
            Location location = new Location(district.getId(),district.getName(), department.getId() ,department.getName(), country.getId(), country.getName());
            UserDto userResource = users.stream().filter(u -> u.getId().equals(product.getUserId())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getDesiredObject(),
                    product.getPrice(),
                    product.getImage(),
                    product.getBoost(),
                    product.getAvailable(),
                    userResource,
                    productCategory,
                    location,
                    product.getCreatedAt()
            );
        }).collect(Collectors.toList());
    }
}
