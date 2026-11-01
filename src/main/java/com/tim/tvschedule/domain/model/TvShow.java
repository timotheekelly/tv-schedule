package com.tim.tvschedule.domain.model;

import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("program_content")
public final class TvShow extends ProgramContent {

    private String tagline;
    private String firstAirDate;
    private String lastAirDate;
    private Integer releaseYear;
    private Integer numberOfSeasons;
    private Integer numberOfEpisodes;
    private List<Integer> episodeRuntimeMinutes;
    private List<String> networks;

    @PersistenceCreator
    public TvShow(
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
            String firstAirDate,
            String lastAirDate,
            Integer releaseYear,
            Integer numberOfSeasons,
            Integer numberOfEpisodes,
            List<Integer> episodeRuntimeMinutes,
            List<String> networks
    ) {
        super(
                id,
                ProgramContentType.TV_SHOW,
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
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.releaseYear = releaseYear;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.episodeRuntimeMinutes = episodeRuntimeMinutes;
        this.networks = networks;
    }

    public String getTagline() {
        return tagline;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public List<Integer> getEpisodeRuntimeMinutes() {
        return episodeRuntimeMinutes;
    }

    public List<String> getNetworks() {
        return networks;
    }
}
