package com.tim.tvschedule.infrastructure.repository.mongodb.document;

public class MovieDocument extends ProgramContentDocument {

    private Integer runtimeMinutes;

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }
}
