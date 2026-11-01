package com.tim.tvschedule.infrastructure.tmdb.model.details.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TmdbTvShowDetailsApiResult(

        Long id,

        String name,

        @JsonProperty("original_name")
        String originalName,

        String overview,

        String tagline,

        String status,

        @JsonProperty("original_language")
        String originalLanguage,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("backdrop_path")
        String backdropPath,

        @JsonProperty("first_air_date")
        String firstAirDate,

        @JsonProperty("last_air_date")
        String lastAirDate,

        @JsonProperty("number_of_seasons")
        Integer numberOfSeasons,

        @JsonProperty("number_of_episodes")
        Integer numberOfEpisodes,

        @JsonProperty("episode_run_time")
        List<Integer> episodeRunTime,

        @JsonProperty("vote_average")
        Double voteAverage,

        @JsonProperty("vote_count")
        Integer voteCount,

        List<Genre> genres,

        List<Network> networks,

        @JsonProperty("external_ids")
        ExternalIds externalIds

) {

        public record Genre(
                Integer id,
                String name
        ) {
        }

        public record Network(
                Integer id,
                String name
        ) {
        }

        public record ExternalIds(
                @JsonProperty("imdb_id")
                String imdbId
        ) {
        }
}
