package com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded;

public class BackdropDocument {
    private String tmdbPath;

    private String fullUrl;

    public String getTmdbPath() {
        return tmdbPath;
    }

    public void setTmdbPath(String tmdbPath) {
        this.tmdbPath = tmdbPath;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
