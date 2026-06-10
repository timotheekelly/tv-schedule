package com.tim.tvschedule.infrastructure.repository.mongodb.program.document;

import com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded.BackdropDocument;
import com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded.PosterDocument;
import com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded.RatingsDocument;
import com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded.StreamingAvailabilityDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("program_content")
public abstract class ProgramContentDocument {

    @Id
    private String id;

    private String contentType;

    private String title;

    private String slug;

    private Integer releaseYear;

    private String description;

    private List<String> genres;

    private List<String> themes;

    private List<String> moods;

    private PosterDocument poster;

    private BackdropDocument backdrop;

    private RatingsDocument ratings;

    private List<StreamingAvailabilityDocument> streamingAvailability;

    private Instant createdAt;

    private Instant updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public List<String> getMoods() {
        return moods;
    }

    public void setMoods(List<String> moods) {
        this.moods = moods;
    }

    public PosterDocument getPoster() {
        return poster;
    }

    public void setPoster(PosterDocument poster) {
        this.poster = poster;
    }

    public BackdropDocument getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(BackdropDocument backdrop) {
        this.backdrop = backdrop;
    }

    public RatingsDocument getRatings() {
        return ratings;
    }

    public void setRatings(RatingsDocument ratings) {
        this.ratings = ratings;
    }

    public List<StreamingAvailabilityDocument> getStreamingAvailability() {
        return streamingAvailability;
    }

    public void setStreamingAvailability(List<StreamingAvailabilityDocument> streamingAvailability) {
        this.streamingAvailability = streamingAvailability;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}