package com.tim.tvschedule.infrastructure.tmdb.model.search.movie;

public record TmdbMovieSearchResult(

        Long tmdbId,

        String title,

        String overview,

        String releaseDate,

        String posterPath

) {
}