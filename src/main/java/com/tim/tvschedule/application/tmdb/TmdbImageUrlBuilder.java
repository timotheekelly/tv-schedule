package com.tim.tvschedule.infrastructure.tmdb;

import org.springframework.stereotype.Component;

@Component
public class TmdbImageUrlBuilder {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/";

    public String posterUrl(String posterPath) {
        if (posterPath == null || posterPath.isBlank()) {
            return null;
        }

        return BASE_URL + "w500" + posterPath;
    }
}