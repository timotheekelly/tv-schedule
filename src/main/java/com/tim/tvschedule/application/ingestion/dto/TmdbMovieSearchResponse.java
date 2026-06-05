package com.tim.tvschedule.application.ingestion.dto;

public record TmdbMovieSearchResponse(

        Long tmdbId,

        String title,

        String description,

        Integer releaseYear,

        String posterUrl
) {}