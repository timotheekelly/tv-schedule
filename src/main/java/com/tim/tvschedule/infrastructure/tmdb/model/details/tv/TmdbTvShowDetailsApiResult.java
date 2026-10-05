package com.tim.tvschedule.infrastructure.tmdb.model.details.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbTvShowDetailsApiResult(

        Long id,

        String name,

        String overview,

        String tagline,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("backdrop_path")
        String backdropPath,

        @JsonProperty("first_air_date")
        String firstAirDate,

        List<Genre> genres

) {

        public record Genre(
                Integer id,
                String name
        ) {
        }
}
