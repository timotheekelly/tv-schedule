package com.tim.tvschedule.application.web.controller;

import com.tim.tvschedule.application.web.dto.DailyScheduleResponse;
import com.tim.tvschedule.application.web.mapper.ScheduleResponseMapper;
import com.tim.tvschedule.domain.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleResponseMapper scheduleResponseMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleResponseMapper scheduleResponseMapper) {
        this.scheduleService = scheduleService;
        this.scheduleResponseMapper = scheduleResponseMapper;
    }

    @GetMapping
    public List<DailyScheduleResponse> getSchedule() {
        return scheduleResponseMapper.toDailyScheduleResponse(
                scheduleService.getSchedule()
        );
    }
}