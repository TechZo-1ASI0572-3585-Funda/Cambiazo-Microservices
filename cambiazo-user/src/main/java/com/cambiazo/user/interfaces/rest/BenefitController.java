package com.cambiazo.user.interfaces.rest;


import com.cambiazo.user.domain.model.queries.GetAllBenefitsQuery;
import com.cambiazo.user.domain.model.queries.GetBenefitByIdQuery;
import com.cambiazo.user.domain.services.IBenefitCommandService;
import com.cambiazo.user.domain.services.IBenefitQueryService;
import com.cambiazo.user.interfaces.rest.resources.BenefitResource;
import com.cambiazo.user.interfaces.rest.resources.CreateBenefitResource;
import com.cambiazo.user.interfaces.rest.transform.BenefitResourceFromEntityAssembler;
import com.cambiazo.user.interfaces.rest.transform.CreateBenefitCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v2/benefits")
@Tag(name = "Benefits", description = "Benefits Management Endpoints")
public class BenefitController {
    private final IBenefitCommandService benefitCommandService;

    private final IBenefitQueryService benefitQueryService;

    public BenefitController(IBenefitCommandService benefitCommandService, IBenefitQueryService benefitQueryService) {
        this.benefitCommandService = benefitCommandService;
        this.benefitQueryService = benefitQueryService;
    }


    @Operation(summary = "Create a new Benefit", description = "Create a new Benefit with the input data.")
    @PostMapping
    public ResponseEntity<BenefitResource>createBenefit(@RequestBody CreateBenefitResource resource) {
        try{
            var createBenefitCommand= CreateBenefitCommandFromResourceAssembler.toCommandFromResource(resource);
            var benefit= benefitCommandService.handle(createBenefitCommand);
            var benefitResource= BenefitResourceFromEntityAssembler.toResourceFromEntity(benefit.get());
            return ResponseEntity.status(CREATED).body(benefitResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenefitResource>getBenefitById(@PathVariable Long id){
        try{
            var getBenefitByIdQuery = new GetBenefitByIdQuery(id);
            var benefit = benefitQueryService.handle(getBenefitByIdQuery);
            var benefitResource = BenefitResourceFromEntityAssembler.toResourceFromEntity(benefit.get());
            return ResponseEntity.ok(benefitResource);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BenefitResource>>getAllBenefits(){
        try{
            var getAllBenefitsQuery = new GetAllBenefitsQuery();
            var benefits = benefitQueryService.handle(getAllBenefitsQuery);
            var benefitResource = benefits.stream()
                    .map(BenefitResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();
            return ResponseEntity.ok(benefitResource);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
