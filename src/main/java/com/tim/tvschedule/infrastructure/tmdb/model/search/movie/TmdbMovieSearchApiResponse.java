package com.tim.tvschedule.infrastructure.tmdb.model.search;

import java.util.List;

public record TmdbMovieSearchApiResponse(
        List<TmdbMovieSearchApiResult> results
) {
}
