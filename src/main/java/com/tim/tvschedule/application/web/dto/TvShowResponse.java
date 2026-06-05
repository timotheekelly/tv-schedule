package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.StreamingPlatform;

public record TvShowResponse(
        String id,
        String title,
        ProgramContentType type,
        String description,
        String posterPath,
        String posterUrl,
        String backdropPath,
        String backdropUrl,
        String rottenTomatoesUrl,
        StreamingPlatform streamingPlatform
) implements ProgramContentResponse {
}