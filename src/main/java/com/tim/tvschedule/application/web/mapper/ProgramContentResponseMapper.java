package com.tim.tvschedule.application.web.mapper;

import com.tim.tvschedule.application.tmdb.TmdbImageUrlBuilder;
import com.tim.tvschedule.application.web.dto.MovieResponse;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.dto.TvShowResponse;
import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.TvShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProgramContentResponseMapper {

    private final TmdbImageUrlBuilder tmdbImageUrlBuilder;

    public ProgramContentResponseMapper(TmdbImageUrlBuilder tmdbImageUrlBuilder) {
        this.tmdbImageUrlBuilder = tmdbImageUrlBuilder;
    }

    public List<ProgramContentResponse> toProgramContentResponse(List<ProgramContent> programs) {
        return programs.stream()
                .map(this::toProgramContentResponse)
                .toList();
    }

    public ProgramContentResponse toProgramContentResponse(ProgramContent content) {
        return switch (content) {
            case Movie movie -> toMovieResponse(movie);
            case TvShow tvShow -> toTvShowResponse(tvShow);
            default -> throw new IllegalStateException("Unexpected value: " + content);
        };
    }

    private MovieResponse toMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getType(),
                movie.getDescription(),
                movie.getPosterPath(),
                tmdbImageUrlBuilder.buildPosterUrl(movie.getPosterPath()),
                movie.getBackdropPath(),
                tmdbImageUrlBuilder.buildBackdropUrl(movie.getBackdropPath()),
                movie.getRottenTomatoesUrl(),
                movie.getStreamingPlatform()
        );
    }

    private TvShowResponse toTvShowResponse(TvShow tvShow) {
        return new TvShowResponse(
                tvShow.getId(),
                tvShow.getTitle(),
                tvShow.getType(),
                tvShow.getDescription(),
                tvShow.getPosterPath(),
                tmdbImageUrlBuilder.buildPosterUrl(tvShow.getPosterPath()),
                tvShow.getBackdropPath(),
                tmdbImageUrlBuilder.buildBackdropUrl(tvShow.getBackdropPath()),
                tvShow.getRottenTomatoesUrl(),
                tvShow.getStreamingPlatform()
        );
    }
}