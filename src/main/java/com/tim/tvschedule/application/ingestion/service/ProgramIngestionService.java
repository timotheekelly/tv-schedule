package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.mapper.TmdbRawProgramMapper;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.mapper.ProgramContentResponseMapper;
import com.tim.tvschedule.domain.enrichment.model.RawProgramData;
import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.TvShow;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.details.tv.TmdbTvShowDetailsApiResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        ProgramContent existing = findExistingByTitle(rawProgram.title());
        if (existing != null) {
            return programContentResponseMapper.toProgramContentResponse(existing);
        }

        ProgramContent movie = buildMovie(rawProgram);

        return programContentResponseMapper.toProgramContentResponse(
                programRepository.save(movie)
        );
    }

    public ProgramContentResponse ingestTvShow(Long tmdbId) {

        TmdbTvShowDetailsApiResult tmdbTvShow =
                tmdbClient.getTvShowDetails(tmdbId);

        RawProgramData rawProgram =
                tmdbRawProgramMapper.toRawProgramData(tmdbTvShow);

        ProgramContent existing = findExistingByTitle(rawProgram.title());
        if (existing != null) {
            return programContentResponseMapper.toProgramContentResponse(existing);
        }

        ProgramContent tvShow = buildTvShow(rawProgram);

        return programContentResponseMapper.toProgramContentResponse(
                programRepository.save(tvShow)
        );
    }

    private ProgramContent findExistingByTitle(String title) {
        return programRepository.findAll().stream()
                .filter(program -> program.getTitle() != null
                        && program.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    private Movie buildMovie(RawProgramData rawProgram) {
        return new Movie(
                "movie-" + UUID.randomUUID(),
                rawProgram.title(),
                rawProgram.overview(),
                rawProgram.posterPath(),
                rawProgram.backdropPath(),
                null,
                null
        );
    }

    private TvShow buildTvShow(RawProgramData rawProgram) {
        return new TvShow(
                "tvshow-" + UUID.randomUUID(),
                rawProgram.title(),
                rawProgram.overview(),
                rawProgram.posterPath(),
                rawProgram.backdropPath(),
                null,
                null
        );
    }
}
