package com.tim.tvschedule.domain.model;

import org.springframework.data.annotation.Id;

public abstract class ProgramContent {

    @Id
    private String id;

    private ProgramContentType type;
    private String title;
    private String description;
    private String posterPath;
    private String backdropPath;
    private String rottenTomatoesUrl;
    private StreamingPlatform streamingPlatform;
    private Long tmdbId;

    protected ProgramContent() {
    }

    protected ProgramContent(
            String id,
            ProgramContentType type,
            String title,
            String description,
            String posterPath,
            String backdropPath,
            String rottenTomatoesUrl,
            StreamingPlatform streamingPlatform,
            Long tmdbId
    ) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.rottenTomatoesUrl = rottenTomatoesUrl;
        this.streamingPlatform = streamingPlatform;
        this.tmdbId = tmdbId;
    }


    public String getId() {
        return id;
    }

    public ProgramContentType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getRottenTomatoesUrl() {
        return rottenTomatoesUrl;
    }

    public StreamingPlatform getStreamingPlatform() {
        return streamingPlatform;
    }

    public void setStreamingPlatform(StreamingPlatform streamingPlatform) {
        this.streamingPlatform = streamingPlatform;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }
}