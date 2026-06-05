package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.dto.TmdbMovieSearchResponse;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.search.TmdbMovieSearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmdbSearchService {

    private final TmdbClient tmdbClient;

    public TmdbSearchService(TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    public List<TmdbMovieSearchResponse> searchMovies(String query) {

        List<TmdbMovieSearchResult> results =
                tmdbClient.searchMovies(query);

        return results.stream()
                .map(this::toResponse)
                .toList();
    }

    private TmdbMovieSearchResponse toResponse(
            TmdbMovieSearchResult result
    ) {

        Integer releaseYear = null;

        if (result.releaseDate() != null
                && !result.releaseDate().isBlank()
                && result.releaseDate().length() >= 4) {

            releaseYear =
                    Integer.parseInt(result.releaseDate().substring(0, 4));
        }

        return new TmdbMovieSearchResponse(
                result.tmdbId(),
                result.title(),
                result.overview(),
                releaseYear,
                result.posterPath()
        );
    }
}