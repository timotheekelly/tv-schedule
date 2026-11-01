package com.tim.tvschedule.infrastructure.tmdb.model.providers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProgramWatchProviders(
        Long id,
        Map<String, CountryProviders> results
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CountryProviders(
            String link,
            List<WatchProvider> flatrate
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record WatchProvider(
            @JsonProperty("provider_id")
            Integer providerId,

            @JsonProperty("provider_name")
            String providerName,

            @JsonProperty("logo_path")
            String logoPath
    ) {}
}