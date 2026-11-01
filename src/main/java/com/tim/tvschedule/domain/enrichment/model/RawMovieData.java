package com.tim.tvschedule.domain.enrichment.model;

import com.tim.tvschedule.domain.model.BelongsToCollection;

import java.util.List;

public record RawMovieData(
        Long tmdbId,
        String imdbId,
        String title,
        String originalTitle,
        String overview,
        String tagline,
        String status,
        String originalLanguage,
        List<String> genres,
        String posterPath,
        String backdropPath,
        String releaseDate,
        Integer releaseYear,
        Integer runtimeMinutes,
        Double tmdbRatingAverage,
        Integer tmdbRatingVoteCount,
        BelongsToCollection belongsToCollection
) {
}
