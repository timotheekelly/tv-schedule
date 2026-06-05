package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.ScheduleEntry;

import java.util.List;

public record ScheduleEntryResponse(
        List<ScheduleEntry> schedule
) {
}
