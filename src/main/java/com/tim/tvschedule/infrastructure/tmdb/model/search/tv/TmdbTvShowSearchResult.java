package com.tim.tvschedule.infrastructure.tmdb.model.search.tv;

public record TmdbTvShowSearchResult(

        Long tmdbId,

        String title,

        String overview,

        String firstAirDate,

        String posterPath

) {
}