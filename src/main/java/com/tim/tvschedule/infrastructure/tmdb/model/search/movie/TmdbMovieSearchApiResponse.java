package com.tim.tvschedule.infrastructure.tmdb.model.search.movie;

import java.util.List;

public record TmdbMovieSearchApiResponse(
        List<TmdbMovieSearchApiResult> results
) {
}
