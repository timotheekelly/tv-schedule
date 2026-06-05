package com.tim.tvschedule.application.web.controller;

import com.tim.tvschedule.application.web.dto.ProgramContentResponse;
import com.tim.tvschedule.application.web.dto.ProgramRequest;
import com.tim.tvschedule.application.web.mapper.ProgramContentResponseMapper;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;
    private final ProgramContentResponseMapper programContentResponseMapper;

    public ProgramController(
            ProgramService programService,
            ProgramContentResponseMapper programContentResponseMapper
    ) {
        this.programService = programService;
        this.programContentResponseMapper = programContentResponseMapper;
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

    @PostMapping
    public ProgramContentResponse addProgram(@RequestBody ProgramRequest request) {
        ProgramContent program = programService.addProgram(request.toCommand());
        return programContentResponseMapper.toProgramContentResponse(program);
    }

    @PutMapping("/{id}")
    public ProgramContentResponse updateProgram(
            @PathVariable String id,
            @RequestBody ProgramRequest request
    ) {
        ProgramContent program = programService.updateProgram(id, request.toCommand());
        return programContentResponseMapper.toProgramContentResponse(program);
    }

    @DeleteMapping("/{id}")
    public void deleteProgram(@PathVariable String id) {
        programService.deleteProgram(id);
    }
}