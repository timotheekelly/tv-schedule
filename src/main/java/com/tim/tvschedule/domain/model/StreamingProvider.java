package com.tim.tvschedule.domain.model;

public record StreamingProvider(
        Integer providerId,
        String providerName,
        String logoPath
) {
}
