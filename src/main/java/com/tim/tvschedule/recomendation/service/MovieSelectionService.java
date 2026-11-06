package com.tim.tvschedule.recomendation.service;

import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.search.movie.TmdbMovieSearchResult;
import com.tim.tvschedule.recomendation.model.MovieRecommendation;
import com.tim.tvschedule.recomendation.model.MovieRecommendations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieSelectionService {

    private final MovieRecommendationService recommendationService;
    private final MovieCatalogueService catalogueService;
    private final TmdbClient tmdbClient;

    public MovieSelectionService(
            MovieRecommendationService recommendationService,
            MovieCatalogueService catalogueService,
            TmdbClient tmdbClient
    ) {
        this.recommendationService = recommendationService;
        this.catalogueService = catalogueService;
        this.tmdbClient = tmdbClient;
    }

    public List<ProgramContent> selectMovies(String prompt, int count) {

        MovieRecommendations recommendations =
                recommendationService.recommend(prompt, count);

        List<ProgramContent> movies = new ArrayList<>();
        Set<Long> selectedTmdbIds = new HashSet<>();

        for (MovieRecommendation recommendation
                : recommendations.movies()) {

            Long tmdbId = resolveTmdbId(recommendation);

            if (tmdbId == null || !selectedTmdbIds.add(tmdbId)) {
                continue;
            }

            ProgramContent movie = catalogueService.getOrCreateMovie(tmdbId);
            movies.add(movie);
        }

        return movies;
    }

    private Long resolveTmdbId(MovieRecommendation recommendation) {
        List<TmdbMovieSearchResult> results = tmdbClient.searchMovies(recommendation.title(), recommendation.releaseYear());
        return results.getFirst().tmdbId();
    }
}
