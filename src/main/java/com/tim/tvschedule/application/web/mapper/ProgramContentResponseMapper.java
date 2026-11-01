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
        String posterPath = movie.getImages() != null ? movie.getImages().posterPath() : null;
        String backdropPath = movie.getImages() != null ? movie.getImages().backdropPath() : null;

        return new MovieResponse(
                movie.getId(),
                movie.getTmdbId(),
                movie.getImdbId(),
                movie.getType(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getOverview(),
                movie.getTagline(),
                movie.getStatus(),
                movie.getOriginalLanguage(),
                movie.getGenres(),
                movie.getImages(),
                tmdbImageUrlBuilder.buildPosterUrl(posterPath),
                tmdbImageUrlBuilder.buildBackdropUrl(backdropPath),
                movie.getRatings(),
                movie.getBelongsToCollection(),
                movie.getStreamingAvailability(),
                movie.getReleaseDate(),
                movie.getReleaseYear(),
                movie.getRuntimeMinutes(),
                movie.getMetadataUpdatedAt(),
                movie.getCreatedAt()
        );
    }

    private TvShowResponse toTvShowResponse(TvShow tvShow) {
        String posterPath = tvShow.getImages() != null ? tvShow.getImages().posterPath() : null;
        String backdropPath = tvShow.getImages() != null ? tvShow.getImages().backdropPath() : null;

        return new TvShowResponse(
                tvShow.getId(),
                tvShow.getTmdbId(),
                tvShow.getImdbId(),
                tvShow.getType(),
                tvShow.getTitle(),
                tvShow.getOriginalTitle(),
                tvShow.getOverview(),
                tvShow.getTagline(),
                tvShow.getStatus(),
                tvShow.getOriginalLanguage(),
                tvShow.getGenres(),
                tvShow.getImages(),
                tmdbImageUrlBuilder.buildPosterUrl(posterPath),
                tmdbImageUrlBuilder.buildBackdropUrl(backdropPath),
                tvShow.getRatings(),
                tvShow.getStreamingAvailability(),
                tvShow.getFirstAirDate(),
                tvShow.getLastAirDate(),
                tvShow.getReleaseYear(),
                tvShow.getNumberOfSeasons(),
                tvShow.getNumberOfEpisodes(),
                tvShow.getEpisodeRuntimeMinutes(),
                tvShow.getNetworks(),
                tvShow.getMetadataUpdatedAt(),
                tvShow.getCreatedAt()
        );
    }
}
