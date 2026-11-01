package com.tim.tvschedule.domain.model;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("program_content")
public final class Movie extends ProgramContent {

    private String tagline;
    private String releaseDate;
    private Integer releaseYear;
    private Integer runtimeMinutes;
    private BelongsToCollection belongsToCollection;

    @PersistenceCreator
    public Movie(
            String id,
            Long tmdbId,
            String imdbId,
            String title,
            String originalTitle,
            String overview,
            String status,
            String originalLanguage,
            List<String> genres,
            Images images,
            Ratings ratings,
            StreamingAvailability streamingAvailability,
            Instant metadataUpdatedAt,
            Instant createdAt,
            String tagline,
            String releaseDate,
            Integer releaseYear,
            Integer runtimeMinutes,
            BelongsToCollection belongsToCollection
    ) {
        super(
                id,
                ProgramContentType.MOVIE,
                tmdbId,
                imdbId,
                title,
                originalTitle,
                overview,
                status,
                originalLanguage,
                genres,
                images,
                ratings,
                streamingAvailability,
                metadataUpdatedAt,
                createdAt
        );
        this.tagline = tagline;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.runtimeMinutes = runtimeMinutes;
        this.belongsToCollection = belongsToCollection;
    }

    public String getTagline() {
        return tagline;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }
}
