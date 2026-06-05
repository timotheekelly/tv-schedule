package com.tim.tvschedule.application.ingestion.dto;

public record TmdbTvShowSearchResponse(
        Long tmdbId,

        String title,

        String description,

        Integer firstAirDate,

        String posterUrl
) {
}