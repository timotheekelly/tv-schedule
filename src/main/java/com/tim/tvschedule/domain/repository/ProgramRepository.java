package com.tim.tvschedule.infrastructure;

import com.tim.tvschedule.domain.model.ProgramContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository {
    List<ProgramContent> findAll();
}
