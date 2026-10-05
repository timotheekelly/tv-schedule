package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.dto.TmdbTvShowSearchResponse;
import com.tim.tvschedule.application.ingestion.mapper.TmdbTvShowSearchResponseMapper;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.details.tv.TmdbTvShowDetailsApiResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmdbTvShowSearchService {

    private final TmdbClient tmdbClient;
    private final TmdbTvShowSearchResponseMapper mapper;

    public TmdbTvShowSearchService(
            TmdbClient tmdbClient,
            TmdbTvShowSearchResponseMapper mapper
    ) {
        this.tmdbClient = tmdbClient;
        this.mapper = mapper;
    }

    public List<TmdbTvShowSearchResponse> searchTvShows(String query) {

        return tmdbClient.searchTvShows(query)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TmdbTvShowDetailsApiResult getTvShowDetails(Long tmdbId) {
        return tmdbClient.getTvShowDetails(tmdbId);
    }
}