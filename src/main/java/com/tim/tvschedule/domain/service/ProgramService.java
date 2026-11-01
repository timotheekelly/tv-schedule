package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
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
        return programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program not found: " + id));
    }

    public void deleteProgram(String id) {
        findProgramById(id);
        programRepository.deleteById(id);
    }
}
