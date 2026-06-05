package com.tim.tvschedule.domain.repository;

import com.tim.tvschedule.domain.model.ScheduleEntry;

import java.util.List;

public interface ScheduleRepository {
    void clear();

    void saveAll(List<ScheduleEntry> scheduleEntries);

    List<ScheduleEntry> findAll();
}