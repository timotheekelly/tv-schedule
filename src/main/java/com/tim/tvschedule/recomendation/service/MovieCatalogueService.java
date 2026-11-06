package com.tim.tvschedule.recomendation.service;

import com.tim.tvschedule.application.ingestion.service.ProgramIngestionService;
import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieCatalogueService {

    private final ProgramRepository movieRepository;
    private final ProgramIngestionService programIngestionService;

    public MovieCatalogueService(
            ProgramRepository movieRepository,
            ProgramIngestionService programIngestionService
    ) {
        this.movieRepository = movieRepository;
        this.programIngestionService = programIngestionService;
    }

    public ProgramContent getOrCreateMovie(Long tmdbId) {
        return movieRepository.findByTmdbId(tmdbId)
                .orElseGet(() -> {
                    programIngestionService.ingestMovie(tmdbId);

                    return movieRepository.findByTmdbId(tmdbId)
                            .orElseThrow(() -> new IllegalStateException(
                                    "Movie not found after ingestion: " + tmdbId
                            ));
                });
    }
}
