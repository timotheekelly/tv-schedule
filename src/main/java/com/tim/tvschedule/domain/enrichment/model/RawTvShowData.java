package com.tim.tvschedule.domain.enrichment.model;

import java.util.List;

public record RawTvShowData(
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
        String firstAirDate,
        String lastAirDate,
        Integer releaseYear,
        Integer numberOfSeasons,
        Integer numberOfEpisodes,
        List<Integer> episodeRuntimeMinutes,
        List<String> networks,
        Double tmdbRatingAverage,
        Integer tmdbRatingVoteCount
) {
}
