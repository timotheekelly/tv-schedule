package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.application.ingestion.service.ProgramIngestionService;
import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.TvShow;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramIngestionService programIngestionService;

    public ProgramService(ProgramRepository programRepository, ProgramIngestionService programIngestionService) {
        this.programRepository = programRepository;
        this.programIngestionService = programIngestionService;
    }

    public List<ProgramContent> findPrograms(ProgramContentType type) {
        if (type == null) {
            return programRepository.findAll();
        }

        return programRepository.findByType(type);
    }

    public List<ProgramContent> findProgramsByTitle(String title) {
        return programRepository.findByTitle(title);
    }

    public ProgramContent findProgramById(String id) {
        ProgramContent programContent = programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program not found: " + id));

//        if(programContent.getTmdbId() != null) {
//
//        }

        return programContent;
    }


    public void deleteProgram(String id) {
        findProgramById(id);
        programRepository.deleteById(id);
    }
}