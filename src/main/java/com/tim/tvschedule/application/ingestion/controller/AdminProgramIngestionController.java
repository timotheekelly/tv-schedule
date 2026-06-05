package com.tim.tvschedule.application.ingestion.controller;

import com.tim.tvschedule.application.ingestion.dto.TmdbMovieSearchResponse;
import com.tim.tvschedule.application.ingestion.dto.TmdbTvShowSearchResponse;
import com.tim.tvschedule.application.ingestion.service.ProgramIngestionService;
import com.tim.tvschedule.application.ingestion.service.TmdbMovieSearchService;
import com.tim.tvschedule.application.ingestion.service.TmdbTvShowSearchService;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/programs")
public class AdminProgramIngestionController {

    private final TmdbMovieSearchService tmdbMovieSearchService;
    private final TmdbTvShowSearchService tmdbTvShowSearchService;
    private final ProgramIngestionService programIngestionService;

    public AdminProgramIngestionController(
            TmdbMovieSearchService tmdbMovieSearchService,
            TmdbTvShowSearchService tmdbTvShowSearchService,
            ProgramIngestionService programIngestionService
    ) {
        this.tmdbMovieSearchService = tmdbMovieSearchService;
        this.tmdbTvShowSearchService = tmdbTvShowSearchService;
        this.programIngestionService = programIngestionService;
    }

    @GetMapping("/search/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<TmdbMovieSearchResponse> searchMovies(
            @RequestParam String query
    ) {

        return tmdbMovieSearchService.searchMovies(query);
    }

    @GetMapping("/search/tvshows")
    @ResponseStatus(HttpStatus.OK)
    public List<TmdbTvShowSearchResponse> searchTvShows(
            @RequestParam String query
    ) {

        return tmdbTvShowSearchService.searchTvShows(query);
    }

    // Test endpoint to verify movie movie retrieved
    @GetMapping("/movies/{tmdbId}")
    public TmdbMovieDetailsApiResult getMovie(
            @PathVariable Long tmdbId
    ) {
        return tmdbMovieSearchService.getMovieDetails(tmdbId);
    }

    @PostMapping("/movies/{tmdbId}/ingest")
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramContentResponse ingestMovie(
            @PathVariable Long tmdbId
    ) {

        return programIngestionService.ingestMovie(tmdbId);
    }
}