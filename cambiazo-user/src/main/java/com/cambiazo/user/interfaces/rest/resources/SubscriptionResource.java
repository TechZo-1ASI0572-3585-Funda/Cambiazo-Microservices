package com.cambiazo.user.interfaces.rest.resources;

import java.time.LocalDateTime;

public record SubscriptionResource(Long id, String state, Long planId, Long userId, LocalDateTime startDate, LocalDateTime endDate) {
}
