package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.BelongsToCollection;
import com.tim.tvschedule.domain.model.Images;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.Ratings;
import com.tim.tvschedule.domain.model.StreamingAvailability;

import java.time.Instant;
import java.util.List;

public record MovieResponse(
        String id,
        Long tmdbId,
        String imdbId,
        ProgramContentType type,
        String title,
        String originalTitle,
        String overview,
        String tagline,
        String status,
        String originalLanguage,
        List<String> genres,
        Images images,
        String posterUrl,
        String backdropUrl,
        Ratings ratings,
        BelongsToCollection belongsToCollection,
        StreamingAvailability streamingAvailability,
        String releaseDate,
        Integer releaseYear,
        Integer runtimeMinutes,
        Instant metadataUpdatedAt,
        Instant createdAt
) implements ProgramContentResponse {
}
