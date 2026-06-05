package com.tim.tvschedule.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("program_content")
public final class Movie extends ProgramContent {

    public Movie() {
        super();
    }

    public Movie(
            String id,
            String title,
            String description,
            String posterPath,
            String backdropPath,
            String rottenTomatoesUrl,
            StreamingPlatform streamingPlatform
    ) {
        super(
                id,
                ProgramContentType.MOVIE,
                title,
                description,
                posterPath,
                backdropPath,
                rottenTomatoesUrl,
                streamingPlatform
        );
    }
}