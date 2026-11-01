package com.tim.tvschedule.infrastructure.tmdb.model.details.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbMovieDetailsApiResult(

        Long id,

        @JsonProperty("imdb_id")
        String imdbId,

        String title,

        @JsonProperty("original_title")
        String originalTitle,

        String overview,

        String tagline,

        String status,

        @JsonProperty("original_language")
        String originalLanguage,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("backdrop_path")
        String backdropPath,

        @JsonProperty("release_date")
        String releaseDate,

        Integer runtime,

        @JsonProperty("vote_average")
        Double voteAverage,

        @JsonProperty("vote_count")
        Integer voteCount,

        List<Genre> genres,

        @JsonProperty("belongs_to_collection")
        BelongsToCollection belongsToCollection

) {

        public record Genre(
                Integer id,
                String name
        ) {
        }

        public record BelongsToCollection(
                Integer id,
                String name,

                @JsonProperty("poster_path")
                String posterPath,

                @JsonProperty("backdrop_path")
                String backdropPath
        ) {
        }
}
