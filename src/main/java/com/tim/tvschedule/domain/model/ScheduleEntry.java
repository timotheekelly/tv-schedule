package com.tim.tvschedule.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;

@Document("schedule_entries")
public record ScheduleEntry(
        @Id String id,
        DayOfWeek day,
        ScheduleSlotType slotType,
        ProgramContent content
) {
    public ScheduleEntry(DayOfWeek day, ScheduleSlotType slotType, ProgramContent content) {
        this(day + "-" + slotType, day, slotType, content);
    }
}