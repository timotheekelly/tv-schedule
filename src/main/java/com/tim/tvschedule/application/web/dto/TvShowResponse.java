package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.Images;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.Ratings;
import com.tim.tvschedule.domain.model.StreamingAvailability;

import java.time.Instant;
import java.util.List;

public record TvShowResponse(
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
        StreamingAvailability streamingAvailability,
        String firstAirDate,
        String lastAirDate,
        Integer releaseYear,
        Integer numberOfSeasons,
        Integer numberOfEpisodes,
        List<Integer> episodeRuntimeMinutes,
        List<String> networks,
        Instant metadataUpdatedAt,
        Instant createdAt
) implements ProgramContentResponse {
}
