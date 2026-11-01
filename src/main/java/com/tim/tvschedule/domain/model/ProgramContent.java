package com.tim.tvschedule.domain.model;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

public abstract class ProgramContent {

    @Id
    private String id;

    private ProgramContentType type;
    private Long tmdbId;
    private String imdbId;
    private String title;
    private String originalTitle;
    private String overview;
    private String status;
    private String originalLanguage;
    private List<String> genres;
    private Images images;
    private Ratings ratings;
    private StreamingAvailability streamingAvailability;
    private Instant metadataUpdatedAt;
    private Instant createdAt;

    protected ProgramContent() {
    }

    protected ProgramContent(
            String id,
            ProgramContentType type,
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
            Instant createdAt
    ) {
        this.id = id;
        this.type = type;
        this.tmdbId = tmdbId;
        this.imdbId = imdbId;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.status = status;
        this.originalLanguage = originalLanguage;
        this.genres = genres;
        this.images = images;
        this.ratings = ratings;
        this.streamingAvailability = streamingAvailability;
        this.metadataUpdatedAt = metadataUpdatedAt;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public ProgramContentType getType() {
        return type;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getStatus() {
        return status;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public StreamingAvailability getStreamingAvailability() {
        return streamingAvailability;
    }

    public void setStreamingAvailability(StreamingAvailability streamingAvailability) {
        this.streamingAvailability = streamingAvailability;
    }

    public Instant getMetadataUpdatedAt() {
        return metadataUpdatedAt;
    }

    public void setMetadataUpdatedAt(Instant metadataUpdatedAt) {
        this.metadataUpdatedAt = metadataUpdatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
