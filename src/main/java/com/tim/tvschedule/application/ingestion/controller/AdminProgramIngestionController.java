package com.tim.tvschedule.application.ingestion;

import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/programs")
public class AdminProgramIngestionController {

    private final TmdbSearchService tmdbSearchService;
    private final ProgramIngestionService programIngestionService;

    @GetMapping("/search/movies")
    public List<TmdbMovieSearchResponse> searchMovies(
            @RequestParam String query
    ) {
        return tmdbSearchService.searchMovies(query);
    }

    @PostMapping("/movies/{tmdbId}/ingest")
    public ProgramContentResponse ingestMovie(
            @PathVariable Long tmdbId
    ) {
        return programIngestionService.ingestMovie(tmdbId);
    }
}
