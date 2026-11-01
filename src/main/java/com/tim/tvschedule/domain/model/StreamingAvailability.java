package com.tim.tvschedule.domain.model;

import java.time.Instant;
import java.util.List;

public record StreamingAvailability(
        String region,
        List<StreamingProvider> providers,
        String watchUrl,
        Instant lastUpdated
) {
}
