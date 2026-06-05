package com.tim.tvschedule.application.web.dto;

import java.time.DayOfWeek;
import java.util.List;

public record DailyScheduleResponse(
        DayOfWeek day,
        List<ScheduleEntryResponse> entries
) {
}