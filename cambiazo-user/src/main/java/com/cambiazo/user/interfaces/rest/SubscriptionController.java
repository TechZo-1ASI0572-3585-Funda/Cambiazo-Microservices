package com.cambiazo.user.interfaces.rest;


import com.cambiazo.user.domain.model.dto.SubscriptionDto;
import com.cambiazo.user.domain.model.queries.GetActiveSubscriptionByUserIdQuery;
import com.cambiazo.user.domain.model.queries.GetAllSubscriptionsQuery;
import com.cambiazo.user.domain.model.queries.GetSubscriptionByIdQuery;
import com.cambiazo.user.domain.services.ISubscriptionCommandService;
import com.cambiazo.user.domain.services.ISubscriptionQueryService;
import com.cambiazo.user.interfaces.rest.resources.CreateSubscriptionResource;
import com.cambiazo.user.interfaces.rest.resources.SubscriptionResource;
import com.cambiazo.user.interfaces.rest.resources.UpdateSubscriptionResource;
import com.cambiazo.user.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.cambiazo.user.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import com.cambiazo.user.interfaces.rest.transform.UpdateSubscriptionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v2/subscriptions")
@Tag(name = "Subscriptions", description = "Subscriptions Management Endpoints")
public class SubscriptionController {

    private final ISubscriptionCommandService subscriptionCommandService;

    private final ISubscriptionQueryService subscriptionQueryService;

    public SubscriptionController(ISubscriptionCommandService subscriptionCommandService, ISubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }


    @Operation(summary = "Create a new Subscription", description = "Create a new Subscription with the input data.")
    @PostMapping
    public ResponseEntity<SubscriptionResource>createSubscription(@RequestBody CreateSubscriptionResource resource){
        try{
            var createSubscriptionCommand = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
            var subscription = subscriptionCommandService.handle(createSubscriptionCommand);
            var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
            return ResponseEntity.status(CREATED).body(subscriptionResource);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto>getSubscriptionById(@PathVariable Long id){
        try{
            var getSubscriptionByIdQuery = new GetSubscriptionByIdQuery(id);
            var subscription = subscriptionQueryService.handle(getSubscriptionByIdQuery);
            return ResponseEntity.ok(subscription.get());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<SubscriptionDto>getSubscriptionByUserId(@PathVariable Long id){
        try{
            var getSubscriptionByUserIdQuery = new GetActiveSubscriptionByUserIdQuery(id);
            var subscription = subscriptionQueryService.handle(getSubscriptionByUserIdQuery);
            return ResponseEntity.ok(subscription.get());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/active/{userId}")
    public ResponseEntity<SubscriptionDto>getActiveSubscriptionByUserId(@PathVariable Long userId){
        try{
            var getActiveSubscriptionByUserIdQuery = new GetActiveSubscriptionByUserIdQuery(userId);
            var subscription = subscriptionQueryService.handle(getActiveSubscriptionByUserIdQuery);
            return ResponseEntity.ok(subscription.get());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<SubscriptionDto>>getAllSubscriptions(){
        try {
            var getAllSubscriptionQuery = new GetAllSubscriptionsQuery();
            var subscriptions = subscriptionQueryService.handle(getAllSubscriptionQuery);
            return ResponseEntity.ok(subscriptions);
        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update a Subscription", description = "Update a Subscription with the input data.")
    @PutMapping("/status/{subscriptionId}")
    public ResponseEntity<SubscriptionResource>updateSubscriptionStatus(@PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource resource){
        try{
            var updateSubscriptionStatusCommand = UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(subscriptionId, resource);
            var subscription = subscriptionCommandService.handle(updateSubscriptionStatusCommand);
            var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
            return ResponseEntity.ok(subscriptionResource);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
