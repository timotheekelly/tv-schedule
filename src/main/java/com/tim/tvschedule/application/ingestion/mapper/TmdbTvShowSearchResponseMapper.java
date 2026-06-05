package com.tim.tvschedule.application.ingestion.mapper;

import com.tim.tvschedule.application.ingestion.dto.TmdbTvShowSearchResponse;
import com.tim.tvschedule.application.tmdb.TmdbImageUrlBuilder;
import com.tim.tvschedule.infrastructure.tmdb.model.search.tv.TmdbTvShowSearchResult;
import org.springframework.stereotype.Component;

@Component
public class TmdbTvShowSearchResponseMapper {

    TmdbImageUrlBuilder tmdbImageUrlBuilder;

    TmdbTvShowSearchResponseMapper(TmdbImageUrlBuilder tmdbImageUrlBuilder) {
        this.tmdbImageUrlBuilder = tmdbImageUrlBuilder;
    }

    public TmdbTvShowSearchResponse toResponse(
            TmdbTvShowSearchResult result
    ) {

        return new TmdbTvShowSearchResponse(
                result.tmdbId(),
                result.title(),
                result.overview(),
                extractFirstAirDate(result.firstAirDate()),
                tmdbImageUrlBuilder.buildPosterUrl(result.posterPath())
        );
    }

    private Integer extractFirstAirDate(
            String firstAirDate
    ) {

        if (firstAirDate == null || firstAirDate.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(firstAirDate.substring(0, 4));
        } catch (Exception e) {
            return null;
        }
    }
}
