package com.tim.tvschedule.infrastructure.tmdb.model.search.tv;

import java.util.List;

public record TmdbTvShowSearchApiResponse(
        List<TmdbTvShowSearchApiResult> results
) {
}
