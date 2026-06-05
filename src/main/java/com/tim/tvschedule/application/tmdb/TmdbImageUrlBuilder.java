package com.tim.tvschedule.application.tmdb;

import org.springframework.stereotype.Component;

@Component
public class TmdbImageUrlBuilder {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/";

    private static final String POSTER_SIZE = "w500";
    private static final String BACKDROP_SIZE = "w1280";

    public String buildPosterUrl(String posterPath) {
        return buildImageUrl(POSTER_SIZE, posterPath);
    }

    public String buildBackdropUrl(String backdropPath) {
        return buildImageUrl(BACKDROP_SIZE, backdropPath);
    }

    private String buildImageUrl(
            String size,
            String imagePath
    ) {

        if (imagePath == null || imagePath.isBlank()) {
            return null;
        }

        String normalizedImagePath =
                imagePath.startsWith("/")
                        ? imagePath
                        : "/" + imagePath;

        return BASE_URL + size + normalizedImagePath;
    }
}