package com.tim.tvschedule.application.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbMovieDetailsResponse(

        Long id,

        String title,

        String overview,

        String tagline,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("backdrop_path")
        String backdropPath,

        @JsonProperty("release_date")
        String releaseDate,

        Integer runtime,

        List<Genre> genres

) {

    public record Genre(
            Integer id,
            String name
    ) {
    }
}