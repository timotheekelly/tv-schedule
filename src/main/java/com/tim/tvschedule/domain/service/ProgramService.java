package com.tim.tvschedule.domain.service;

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

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<ProgramContent> findPrograms(ProgramContentType type) {
        if (type == null) {
            return programRepository.findAll();
        }

        return programRepository.findByType(type);
    }

    public ProgramContent findProgramById(String id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program not found: " + id));
    }

    public ProgramContent addProgram(ProgramCommand command) {
        ProgramContent program = toProgramContent(createId(command.type()), command);
        return programRepository.save(program);
    }

    public ProgramContent updateProgram(String id, ProgramCommand command) {
        findProgramById(id);

        ProgramContent updatedProgram = toProgramContent(id, command);
        return programRepository.save(updatedProgram);
    }

    public void deleteProgram(String id) {
        findProgramById(id);
        programRepository.deleteById(id);
    }

    private ProgramContent toProgramContent(String id, ProgramCommand command) {
        return switch (command.type()) {
            case MOVIE -> new Movie(
                    id,
                    command.title(),
                    command.description(),
                    command.posterPath(),
                    command.backdropPath(),
                    command.rottenTomatoesUrl(),
                    command.streamingPlatform()
            );
            case TV_SHOW -> new TvShow(
                    id,
                    command.title(),
                    command.description(),
                    command.posterPath(),
                    command.backdropPath(),
                    command.rottenTomatoesUrl(),
                    command.streamingPlatform()
            );
        };
    }

    private String createId(ProgramContentType type) {
        return switch (type) {
            case MOVIE -> "movie-" + UUID.randomUUID();
            case TV_SHOW -> "tvshow-" + UUID.randomUUID();
        };
    }
}