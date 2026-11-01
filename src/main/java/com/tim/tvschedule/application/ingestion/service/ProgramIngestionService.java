package com.tim.tvschedule.application.ingestion.service;

import com.tim.tvschedule.application.ingestion.mapper.TmdbRawProgramMapper;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.mapper.ProgramContentResponseMapper;
import com.tim.tvschedule.domain.enrichment.model.RawMovieData;
import com.tim.tvschedule.domain.enrichment.model.RawTvShowData;
import com.tim.tvschedule.domain.model.Images;
import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.Ratings;
import com.tim.tvschedule.domain.model.RottenTomatoesRating;
import com.tim.tvschedule.domain.model.StreamingAvailability;
import com.tim.tvschedule.domain.model.StreamingProvider;
import com.tim.tvschedule.domain.model.TmdbRating;
import com.tim.tvschedule.domain.model.TvShow;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import com.tim.tvschedule.infrastructure.tmdb.client.TmdbClient;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.details.tv.TmdbTvShowDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.providers.ProgramWatchProviders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProgramIngestionService {

    private static final String WATCH_PROVIDER_REGION = "GB";

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
        TmdbMovieDetailsApiResult tmdbMovie = tmdbClient.getMovieDetails(tmdbId);
        RawMovieData rawMovie = tmdbRawProgramMapper.toRawMovieData(tmdbMovie);

        ProgramContent existing = findExistingByTmdbId(rawMovie.tmdbId());
        StreamingAvailability streamingAvailability = findStreamingAvailabilityForMovie(tmdbId);

        Movie movie = buildMovie(rawMovie, streamingAvailability, existing);

        return programContentResponseMapper.toProgramContentResponse(
                programRepository.save(movie)
        );
    }

    public ProgramContentResponse ingestTvShow(Long tmdbId) {
        TmdbTvShowDetailsApiResult tmdbTvShow = tmdbClient.getTvShowDetails(tmdbId);
        RawTvShowData rawTvShow = tmdbRawProgramMapper.toRawTvShowData(tmdbTvShow);

        ProgramContent existing = findExistingByTmdbId(rawTvShow.tmdbId());
        StreamingAvailability streamingAvailability = findStreamingAvailabilityForTvShow(tmdbId);

        TvShow tvShow = buildTvShow(rawTvShow, streamingAvailability, existing);

        return programContentResponseMapper.toProgramContentResponse(
                programRepository.save(tvShow)
        );
    }

    public void clearAllPrograms() {
        programRepository.deleteAll();
    }

    public List<StreamingProvider> findWatchProvidersForMovie(Long tmdbId) {
        return findStreamingAvailabilityForMovie(tmdbId).providers();
    }

    private StreamingAvailability findStreamingAvailabilityForMovie(Long tmdbId) {
        return toStreamingAvailability(tmdbClient.getMovieWatchProviders(tmdbId));
    }

    private StreamingAvailability findStreamingAvailabilityForTvShow(Long tmdbId) {
        return toStreamingAvailability(tmdbClient.getTvShowWatchProviders(tmdbId));
    }

    private StreamingAvailability toStreamingAvailability(ProgramWatchProviders response) {
        if (response == null || response.results() == null) {
            return new StreamingAvailability(WATCH_PROVIDER_REGION, List.of(), null, Instant.now());
        }

        ProgramWatchProviders.CountryProviders regionProviders = response.results().get(WATCH_PROVIDER_REGION);

        if (regionProviders == null) {
            return new StreamingAvailability(WATCH_PROVIDER_REGION, List.of(), null, Instant.now());
        }

        List<StreamingProvider> providers = regionProviders.flatrate() != null
                ? regionProviders.flatrate().stream().map(this::toStreamingProvider).toList()
                : List.of();

        return new StreamingAvailability(WATCH_PROVIDER_REGION, providers, regionProviders.link(), Instant.now());
    }

    private StreamingProvider toStreamingProvider(ProgramWatchProviders.WatchProvider provider) {
        return new StreamingProvider(provider.providerId(), provider.providerName(), provider.logoPath());
    }

    private ProgramContent findExistingByTmdbId(Long tmdbId) {
        if (tmdbId == null) {
            return null;
        }

        return programRepository.findAll().stream()
                .filter(program -> tmdbId.equals(program.getTmdbId()))
                .findFirst()
                .orElse(null);
    }

    private Movie buildMovie(RawMovieData rawMovie, StreamingAvailability streamingAvailability, ProgramContent existing) {
        String id = existing != null ? existing.getId() : "movie-" + UUID.randomUUID();
        Instant createdAt = existing != null ? existing.getCreatedAt() : Instant.now();

        return new Movie(
                id,
                rawMovie.tmdbId(),
                rawMovie.imdbId(),
                rawMovie.title(),
                rawMovie.originalTitle(),
                rawMovie.overview(),
                rawMovie.status(),
                rawMovie.originalLanguage(),
                rawMovie.genres(),
                new Images(rawMovie.posterPath(), rawMovie.backdropPath()),
                new Ratings(
                        new TmdbRating(rawMovie.tmdbRatingAverage(), rawMovie.tmdbRatingVoteCount()),
                        rottenTomatoesOf(existing)
                ),
                streamingAvailability,
                Instant.now(),
                createdAt,
                rawMovie.tagline(),
                rawMovie.releaseDate(),
                rawMovie.releaseYear(),
                rawMovie.runtimeMinutes(),
                rawMovie.belongsToCollection()
        );
    }

    private TvShow buildTvShow(RawTvShowData rawTvShow, StreamingAvailability streamingAvailability, ProgramContent existing) {
        String id = existing != null ? existing.getId() : "tvshow-" + UUID.randomUUID();
        Instant createdAt = existing != null ? existing.getCreatedAt() : Instant.now();

        return new TvShow(
                id,
                rawTvShow.tmdbId(),
                rawTvShow.imdbId(),
                rawTvShow.title(),
                rawTvShow.originalTitle(),
                rawTvShow.overview(),
                rawTvShow.status(),
                rawTvShow.originalLanguage(),
                rawTvShow.genres(),
                new Images(rawTvShow.posterPath(), rawTvShow.backdropPath()),
                new Ratings(
                        new TmdbRating(rawTvShow.tmdbRatingAverage(), rawTvShow.tmdbRatingVoteCount()),
                        rottenTomatoesOf(existing)
                ),
                streamingAvailability,
                Instant.now(),
                createdAt,
                rawTvShow.tagline(),
                rawTvShow.firstAirDate(),
                rawTvShow.lastAirDate(),
                rawTvShow.releaseYear(),
                rawTvShow.numberOfSeasons(),
                rawTvShow.numberOfEpisodes(),
                rawTvShow.episodeRuntimeMinutes(),
                rawTvShow.networks()
        );
    }

    /**
     * Re-ingesting an existing title would otherwise wipe out a manually curated
     * Rotten Tomatoes score, since TMDB has no such data to repopulate it with.
     */
    private RottenTomatoesRating rottenTomatoesOf(ProgramContent existing) {
        if (existing == null || existing.getRatings() == null) {
            return null;
        }

        return existing.getRatings().rottenTomatoes();
    }
}
