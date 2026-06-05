package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.StreamingPlatform;
import com.tim.tvschedule.domain.service.ProgramCommand;

public record ProgramRequest(
        ProgramContentType type,
        String title,
        String description,
        String posterPath,
        String backdropPath,
        String rottenTomatoesUrl,
        StreamingPlatform streamingPlatform
) {
    public ProgramCommand toCommand() {
        return new ProgramCommand(
                type,
                title,
                description,
                posterPath,
                backdropPath,
                rottenTomatoesUrl,
                streamingPlatform
        );
    }
}