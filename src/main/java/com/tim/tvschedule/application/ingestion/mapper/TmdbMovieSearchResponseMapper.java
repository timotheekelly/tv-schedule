package com.tim.tvschedule.application.ingestion.mapper;

import com.tim.tvschedule.application.ingestion.dto.TmdbMovieSearchResponse;
import com.tim.tvschedule.application.tmdb.TmdbImageUrlBuilder;
import com.tim.tvschedule.infrastructure.tmdb.model.search.movie.TmdbMovieSearchResult;
import org.springframework.stereotype.Component;

@Component
public class TmdbMovieSearchResponseMapper {

    TmdbImageUrlBuilder tmdbImageUrlBuilder;

    TmdbMovieSearchResponseMapper(TmdbImageUrlBuilder tmdbImageUrlBuilder) {
        this.tmdbImageUrlBuilder = tmdbImageUrlBuilder;
    }

    public TmdbMovieSearchResponse toResponse(
            TmdbMovieSearchResult result
    ) {

        return new TmdbMovieSearchResponse(
                result.tmdbId(),
                result.title(),
                result.overview(),
                extractReleaseYear(result.releaseDate()),
                tmdbImageUrlBuilder.buildPosterUrl(result.posterPath())
        );
    }

    private Integer extractReleaseYear(
            String releaseDate
    ) {

        if (releaseDate == null || releaseDate.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(releaseDate.substring(0, 4));
        } catch (Exception e) {
            return null;
        }
    }
}