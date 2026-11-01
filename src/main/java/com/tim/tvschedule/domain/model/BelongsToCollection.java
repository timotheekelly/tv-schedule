package com.tim.tvschedule.domain.model;

public record BelongsToCollection(
        Integer id,
        String name,
        String posterPath,
        String backdropPath
) {
}
