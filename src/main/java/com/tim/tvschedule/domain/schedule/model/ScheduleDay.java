package com.tim.tvschedule.domain.schedule.model;

import java.time.LocalDate;
import java.util.List;

public record ScheduleDay(
        LocalDate date,
        List<String> movieIds,
        List<String> tvIds
) {
}
