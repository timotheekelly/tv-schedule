package com.tim.tvschedule.application.web.controller;

import com.tim.tvschedule.application.ingestion.service.ProgramIngestionService;
import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.mapper.ProgramContentResponseMapper;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.StreamingProvider;
import com.tim.tvschedule.domain.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;
    private final ProgramContentResponseMapper programContentResponseMapper;
    private final ProgramIngestionService programIngestionService;

    public ProgramController(
            ProgramService programService,
            ProgramContentResponseMapper programContentResponseMapper,
            ProgramIngestionService programIngestionService) {
        this.programService = programService;
        this.programContentResponseMapper = programContentResponseMapper;
        this.programIngestionService = programIngestionService;
    }

    @GetMapping
    public List<ProgramContentResponse> getPrograms(
            @RequestParam(required = false) ProgramContentType type
    ) {
        return programContentResponseMapper.toProgramContentResponse(
                programService.findPrograms(type)
        );
    }

    @GetMapping("/{id}")
    public ProgramContentResponse getProgramById(@PathVariable String id) {
        ProgramContent program = programService.findProgramById(id);
        return programContentResponseMapper.toProgramContentResponse(program);
    }

    @GetMapping("/programs/{title}")
    public List<ProgramContentResponse> getProgramByTitle(@PathVariable String title) {
        return programContentResponseMapper
                .toProgramContentResponse(
                        programService.findProgramsByTitle(title)
                );
    }

    // TODO Test endpoint
    @GetMapping("/test/{tmdbId}")
    public List<StreamingProvider> getWatchProvidersForMovie(
            @PathVariable Long tmdbId
    ) {
        return programIngestionService.findWatchProvidersForMovie(tmdbId);
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable String id) {
        programService.deleteProgram(id);
    }
}