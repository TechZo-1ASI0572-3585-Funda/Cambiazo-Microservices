package com.cambiazo.user.application.internal.commandservices;

import com.cambiazo.user.domain.model.commands.CreateSubscriptionCommand;
import com.cambiazo.user.domain.model.commands.UpdateSubscriptionCommand;
import com.cambiazo.user.domain.model.entities.Plan;
import com.cambiazo.user.domain.model.entities.Subscription;
import com.cambiazo.user.domain.services.ISubscriptionCommandService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IPlanRepository;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.ISubscriptionRepository;
import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements ISubscriptionCommandService {

    private final ISubscriptionRepository subscriptionRepository;
    private final IPlanRepository planRepository;

    private final UserRepository userRepository;

    public SubscriptionCommandServiceImpl(ISubscriptionRepository subscriptionRepository, IPlanRepository planRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Optional<Subscription>handle(CreateSubscriptionCommand command){

        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with the given id does not exist"));
        var result=subscriptionRepository.findSubscriptionActiveByUserId(user);

        if(result.isPresent()){
            var subscriptionToUpdate = result.get();
            LocalDateTime endDate = LocalDateTime.now();
            Plan plan = planRepository.findById(subscriptionToUpdate.getPlanId())
                    .orElseThrow(() -> new IllegalArgumentException("Plan with the given id does not exist"));
            subscriptionRepository.save(subscriptionToUpdate.updateInformation(subscriptionToUpdate.getStartDate(),endDate,"Inactivo",plan,user));
        }

        Plan plan = planRepository.findById(command.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan with the given id does not exist"));
        var subscription = new Subscription(command, plan, user);
        subscriptionRepository.save(subscription);
        return Optional.of(subscription);
    }

    @Override
    @Transactional
    public Optional<Subscription>handle(UpdateSubscriptionCommand command){
        var result = subscriptionRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Subscription does not exist");
        }
        var subscriptionToUpdate = result.get();
        try {
            Subscription subscription = subscriptionRepository.findById(command.id())
                    .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

            Plan plan = planRepository.findById(subscription.getPlanId())
                    .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

            User user = userRepository.findById(subscription.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            LocalDateTime startDate = LocalDateTime.now();
            LocalDateTime endDate = startDate.plusMonths(1);


            var updatedSubscription = subscriptionRepository.save(subscriptionToUpdate.updateInformation(startDate,endDate,command.state(),plan,user));
            return Optional.of(updatedSubscription);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating subscription: " + e.getMessage());
        }
    }
}
