package com.tim.tvschedule.domain.repository;

import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository {

    List<ProgramContent> findAll();

    List<ProgramContent> findByType(ProgramContentType type);

    Optional<ProgramContent> findById(String id);

    ProgramContent save(ProgramContent program);

    void saveAll(List<ProgramContent> programs);

    void deleteById(String id);

    void deleteAll();
}
