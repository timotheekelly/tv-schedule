package com.tim.tvschedule.application.web.dto;

import com.tim.tvschedule.domain.model.ScheduleSlotType;

public record ScheduleEntryResponse(
        ScheduleSlotType slotType,
        ProgramContentResponse content
) {
}