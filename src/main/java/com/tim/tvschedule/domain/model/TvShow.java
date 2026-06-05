package com.tim.tvschedule.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("program_content")
public final class TvShow extends ProgramContent {

    public TvShow() {
        super();
    }

    public TvShow(
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
                ProgramContentType.TV_SHOW,
                title,
                description,
                posterPath,
                backdropPath,
                rottenTomatoesUrl,
                streamingPlatform
        );
    }
}