package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.dto.TmdbMovieSearchResponse;
import com.tim.tvschedule.application.ingestion.mapper.TmdbMovieSearchResponseMapper;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmdbMovieSearchService {

    private final TmdbClient tmdbClient;
    private final TmdbMovieSearchResponseMapper mapper;

    public TmdbMovieSearchService(
            TmdbClient tmdbClient,
            TmdbMovieSearchResponseMapper mapper
    ) {
        this.tmdbClient = tmdbClient;
        this.mapper = mapper;
    }

    public List<TmdbMovieSearchResponse> searchMovies(String query) {

        return tmdbClient.searchMovies(query)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TmdbMovieDetailsApiResult getMovieDetails(Long tmdbId) {
        return tmdbClient.getMovieDetails(tmdbId);
    }

}