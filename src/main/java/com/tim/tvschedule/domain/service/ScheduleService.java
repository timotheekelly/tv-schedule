package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.domain.model.ScheduleEntry;
import com.tim.tvschedule.domain.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleEntry> getSchedule() {
        return scheduleRepository.findAll();
    }
}