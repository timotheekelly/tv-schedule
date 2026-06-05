package com.tim.tvschedule.infrastructure.tmdb.model.details.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbMovieDetailsApiResult(

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