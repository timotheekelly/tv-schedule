package com.tim.tvschedule.infrastructure.tmdb.client;

import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.search.movie.TmdbMovieSearchApiResponse;
import com.tim.tvschedule.infrastructure.tmdb.model.search.movie.TmdbMovieSearchResult;
import com.tim.tvschedule.infrastructure.tmdb.model.search.tv.TmdbTvShowSearchApiResponse;
import com.tim.tvschedule.infrastructure.tmdb.model.search.tv.TmdbTvShowSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TmdbClient {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(TmdbClient.class);

    private final RestClient restClient;

    public TmdbClient(
            @Value("${tmdb.api.key}") String apiKey,
            @Value("${tmdb.base-url:https://api.themoviedb.org/3}") String baseUrl
    ) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + apiKey
                )
                .defaultHeader(
                        HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();
    }

    public List<TmdbMovieSearchResult> searchMovies(
            String query
    ) {

        LOGGER.info("Searching TMDB for movie '{}'", query);

        TmdbMovieSearchApiResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", query)
                        .queryParam("include_adult", false)
                        .queryParam("language", "en-US")
                        .build()
                )
                .retrieve()
                .body(TmdbMovieSearchApiResponse.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results()
                .stream()
                .map(result -> new TmdbMovieSearchResult(
                        result.id(),
                        result.title(),
                        result.overview(),
                        result.releaseDate(),
                        result.posterPath()
                ))
                .toList();
    }

    public TmdbMovieDetailsApiResult getMovieDetails(Long tmdbId) {
        LOGGER.info("Fetching TMDB movie details for movie '{}'", tmdbId);

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}")
                        .queryParam("language", "en-US")
                        .build(tmdbId)
                )
                .retrieve()
                .body(TmdbMovieDetailsApiResult.class);
    }

    public List<TmdbTvShowSearchResult> searchTvShows(String query) {
        LOGGER.info("Searching TMDB for tv show '{}'", query);

        TmdbTvShowSearchApiResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/tv")
                        .queryParam("query", query)
                        .queryParam("include_adult", false)
                        .queryParam("language", "en-US")
                        .build()
                )
                .retrieve()
                .body(TmdbTvShowSearchApiResponse.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results()
                .stream()
                .map(result -> new TmdbTvShowSearchResult(
                        result.id(),
                        result.title(),
                        result.overview(),
                        result.firstAirDate(),
                        result.posterPath()
                ))
                .toList();
    }
}