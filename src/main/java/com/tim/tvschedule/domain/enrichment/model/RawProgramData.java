package com.tim.tvschedule.domain.enrichment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RawProgramData(
        Long id,

        String title,

        String overview,

        String tagline,

        String posterPath,

        String backdropPath,

        String releaseDate,

        Integer runtime,

        List<String> genres

) {}
