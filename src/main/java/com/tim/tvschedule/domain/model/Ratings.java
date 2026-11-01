package com.tim.tvschedule.domain.model;

public record Ratings(
        TmdbRating tmdb,
        RottenTomatoesRating rottenTomatoes
) {
}
