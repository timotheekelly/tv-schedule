package com.tim.tvschedule.infrastructure.repository.mongodb.document.embedded;

public class StreamingAvailabilityDocument {

    private String platform;

    private String region;

    private String url;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}