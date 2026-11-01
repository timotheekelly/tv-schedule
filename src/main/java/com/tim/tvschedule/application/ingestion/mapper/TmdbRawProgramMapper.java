package com.tim.tvschedule.application.ingestion.mapper;

import com.tim.tvschedule.domain.enrichment.model.RawMovieData;
import com.tim.tvschedule.domain.enrichment.model.RawTvShowData;
import com.tim.tvschedule.domain.model.BelongsToCollection;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.details.tv.TmdbTvShowDetailsApiResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TmdbRawProgramMapper {

    public RawMovieData toRawMovieData(TmdbMovieDetailsApiResult tmdbMovie) {
        return new RawMovieData(
                tmdbMovie.id(),
                tmdbMovie.imdbId(),
                tmdbMovie.title(),
                tmdbMovie.originalTitle(),
                tmdbMovie.overview(),
                tmdbMovie.tagline(),
                tmdbMovie.status(),
                tmdbMovie.originalLanguage(),
                toGenreNames(tmdbMovie.genres()),
                tmdbMovie.posterPath(),
                tmdbMovie.backdropPath(),
                tmdbMovie.releaseDate(),
                extractYear(tmdbMovie.releaseDate()),
                tmdbMovie.runtime(),
                tmdbMovie.voteAverage(),
                tmdbMovie.voteCount(),
                toBelongsToCollection(tmdbMovie.belongsToCollection())
        );
    }

    public RawTvShowData toRawTvShowData(TmdbTvShowDetailsApiResult tmdbTvShow) {
        return new RawTvShowData(
                tmdbTvShow.id(),
                tmdbTvShow.externalIds() == null ? null : tmdbTvShow.externalIds().imdbId(),
                tmdbTvShow.name(),
                tmdbTvShow.originalName(),
                tmdbTvShow.overview(),
                tmdbTvShow.tagline(),
                tmdbTvShow.status(),
                tmdbTvShow.originalLanguage(),
                toTvGenreNames(tmdbTvShow.genres()),
                tmdbTvShow.posterPath(),
                tmdbTvShow.backdropPath(),
                tmdbTvShow.firstAirDate(),
                tmdbTvShow.lastAirDate(),
                extractYear(tmdbTvShow.firstAirDate()),
                tmdbTvShow.numberOfSeasons(),
                tmdbTvShow.numberOfEpisodes(),
                tmdbTvShow.episodeRunTime(),
                toNetworkNames(tmdbTvShow.networks()),
                tmdbTvShow.voteAverage(),
                tmdbTvShow.voteCount()
        );
    }

    private BelongsToCollection toBelongsToCollection(
            TmdbMovieDetailsApiResult.BelongsToCollection belongsToCollection
    ) {
        if (belongsToCollection == null) {
            return null;
        }

        return new BelongsToCollection(
                belongsToCollection.id(),
                belongsToCollection.name(),
                belongsToCollection.posterPath(),
                belongsToCollection.backdropPath()
        );
    }

    private List<String> toGenreNames(List<TmdbMovieDetailsApiResult.Genre> genres) {
        return genres == null
                ? List.of()
                : genres.stream().map(TmdbMovieDetailsApiResult.Genre::name).toList();
    }

    private List<String> toTvGenreNames(List<TmdbTvShowDetailsApiResult.Genre> genres) {
        return genres == null
                ? List.of()
                : genres.stream().map(TmdbTvShowDetailsApiResult.Genre::name).toList();
    }

    private List<String> toNetworkNames(List<TmdbTvShowDetailsApiResult.Network> networks) {
        return networks == null
                ? List.of()
                : networks.stream().map(TmdbTvShowDetailsApiResult.Network::name).toList();
    }

    private Integer extractYear(String isoDate) {
        if (isoDate == null || isoDate.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(isoDate.substring(0, 4));
        } catch (Exception e) {
            return null;
        }
    }
}
