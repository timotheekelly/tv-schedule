package com.tim.tvschedule.infrastructure.repository.mongodb.document;

public class TvShowDocument extends ProgramContentDocument {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}