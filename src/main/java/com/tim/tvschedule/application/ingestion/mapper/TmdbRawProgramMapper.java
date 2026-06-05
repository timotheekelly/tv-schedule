package com.tim.tvschedule.application.ingestion.mapper;

import com.tim.tvschedule.domain.enrichment.model.RawProgramData;
import com.tim.tvschedule.infrastructure.tmdb.model.details.movie.TmdbMovieDetailsApiResult;
import org.springframework.stereotype.Component;

@Component
public class TmdbRawProgramMapper {

    public RawProgramData toRawProgramData(TmdbMovieDetailsApiResult tmdbMovie) {
        throw new UnsupportedOperationException(
                "toRawProgramData not implemented yet"
        );
    }
}
