package com.tim.tvschedule.infrastructure.tmdb.model.details.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovieSearchApiResult(

        Long id,

        String title,

        String overview,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("release_date")
        String releaseDate

) {
}