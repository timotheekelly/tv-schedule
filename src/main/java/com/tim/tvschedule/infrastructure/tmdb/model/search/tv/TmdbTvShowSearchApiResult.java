package com.tim.tvschedule.infrastructure.tmdb.model.search.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbTvShowSearchApiResult(

        Long id,

        @JsonProperty("name")
        String title,

        String overview,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("first_air_date")
        String firstAirDate

) {
}