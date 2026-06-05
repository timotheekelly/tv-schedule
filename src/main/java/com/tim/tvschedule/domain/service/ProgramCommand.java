package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.StreamingPlatform;

public record ProgramCommand(
        ProgramContentType type,
        String title,
        String description,
        String posterPath,
        String backdropPath,
        String rottenTomatoesUrl,
        StreamingPlatform streamingPlatform
) {
}
