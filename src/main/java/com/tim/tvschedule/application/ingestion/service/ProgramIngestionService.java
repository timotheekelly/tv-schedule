package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.mapper.TmdbRawProgramMapper;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.mapper.ProgramContentResponseMapper;
import com.tim.tvschedule.domain.enrichment.model.RawProgramData;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import org.springframework.stereotype.Service;

@Service
public class ProgramIngestionService {

    private final TmdbClient tmdbClient;
    private final TmdbRawProgramMapper tmdbRawProgramMapper;
    private final ProgramRepository programRepository;
    private final ProgramContentResponseMapper programContentResponseMapper;

    public ProgramIngestionService(
            TmdbClient tmdbClient,
            TmdbRawProgramMapper tmdbRawProgramMapper,
            ProgramRepository programRepository,
            ProgramContentResponseMapper programContentResponseMapper) {
        this.tmdbClient = tmdbClient;
        this.tmdbRawProgramMapper = tmdbRawProgramMapper;
        this.programRepository = programRepository;
        this.programContentResponseMapper = programContentResponseMapper;
    }

    public ProgramContentResponse ingestMovie(Long tmdbId) {

        TmdbMovieDetailsApiResult tmdbMovie =
                tmdbClient.getMovieDetails(tmdbId);

        RawProgramData rawProgram =
                tmdbRawProgramMapper.toRawProgramData(tmdbMovie);

        // enrichment will be added here later

        ProgramContent movie =
                buildMovie(rawProgram);

        return programContentResponseMapper.toProgramContentResponse(
                programRepository.save(movie)
        );
    }

    private ProgramContent buildMovie(
            RawProgramData rawProgram
    ) {

        // temporary implementation
        // replace later with enrichment pipeline

        throw new UnsupportedOperationException(
                "Movie construction not implemented yet"
        );
    }
}