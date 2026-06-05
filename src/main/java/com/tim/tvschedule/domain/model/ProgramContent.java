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
            StreamingPlatform streamingPlatform
    ) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.rottenTomatoesUrl = rottenTomatoesUrl;
        this.streamingPlatform = streamingPlatform;
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
}