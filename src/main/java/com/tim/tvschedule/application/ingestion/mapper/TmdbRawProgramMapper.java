package com.tim.tvschedule.application.ingestion.mapper;

import com.tim.tvschedule.domain.enrichment.model.RawProgramData;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import com.tim.tvschedule.infrastructure.tmdb.model.details.tv.TmdbTvShowDetailsApiResult;
import org.springframework.stereotype.Component;

@Component
public class TmdbRawProgramMapper {

    public RawProgramData toRawProgramData(TmdbMovieDetailsApiResult tmdbMovie) {
        return new RawProgramData(
                tmdbMovie.id(),
                tmdbMovie.title(),
                tmdbMovie.overview(),
                tmdbMovie.tagline(),
                tmdbMovie.posterPath(),
                tmdbMovie.backdropPath(),
                tmdbMovie.releaseDate(),
                tmdbMovie.runtime(),
                tmdbMovie.genres() == null
                        ? java.util.List.of()
                        : tmdbMovie.genres().stream().map(TmdbMovieDetailsApiResult.Genre::name).toList()
        );
    }

    public RawProgramData toRawProgramData(TmdbTvShowDetailsApiResult tmdbTvShow) {
        return new RawProgramData(
                tmdbTvShow.id(),
                tmdbTvShow.name(),
                tmdbTvShow.overview(),
                tmdbTvShow.tagline(),
                tmdbTvShow.posterPath(),
                tmdbTvShow.backdropPath(),
                tmdbTvShow.firstAirDate(),
                null,
                tmdbTvShow.genres() == null
                        ? java.util.List.of()
                        : tmdbTvShow.genres().stream().map(TmdbTvShowDetailsApiResult.Genre::name).toList()
        );
    }
}
