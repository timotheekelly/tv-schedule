package com.tim.tvschedule.infrastructure.repository.mongodb.program.document.embedded;

public class RatingsDocument {

    private Double imdb;

    private Integer rottenTomatoesAudience;

    private Integer rottenTomatoesCritics;

    public Double getImdb() {
        return imdb;
    }

    public void setImdb(Double imdb) {
        this.imdb = imdb;
    }

    public Integer getRottenTomatoesAudience() {
        return rottenTomatoesAudience;
    }

    public void setRottenTomatoesAudience(Integer rottenTomatoesAudience) {
        this.rottenTomatoesAudience = rottenTomatoesAudience;
    }

    public Integer getRottenTomatoesCritics() {
        return rottenTomatoesCritics;
    }

    public void setRottenTomatoesCritics(Integer rottenTomatoesCritics) {
        this.rottenTomatoesCritics = rottenTomatoesCritics;
    }
}