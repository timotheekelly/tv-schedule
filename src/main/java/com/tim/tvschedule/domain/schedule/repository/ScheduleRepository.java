package com.tim.tvschedule.domain.schedule.repository;

import com.tim.tvschedule.domain.model.ScheduleEntry;
import com.tim.tvschedule.domain.schedule.model.Schedule;

import java.util.List;

public interface ScheduleRepository {
    void clear();

    void save(Schedule scheduleEntry);

    void saveAll(List<Schedule> scheduleEntries);

    List<Schedule> findAll();
}