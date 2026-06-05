package com.tim.tvschedule.application.web.mapper;

import com.tim.tvschedule.application.web.dto.DailyScheduleResponse;
import com.tim.tvschedule.application.web.dto.ScheduleEntryResponse;
import com.tim.tvschedule.domain.model.ScheduleEntry;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScheduleResponseMapper {

    private final ProgramContentResponseMapper programContentResponseMapper;

    public ScheduleResponseMapper(ProgramContentResponseMapper programContentResponseMapper) {
        this.programContentResponseMapper = programContentResponseMapper;
    }

    public List<DailyScheduleResponse> toDailyScheduleResponse(List<ScheduleEntry> entries) {
        Map<DayOfWeek, List<ScheduleEntry>> entriesByDay = entries.stream()
                .collect(Collectors.groupingBy(ScheduleEntry::day));

        return entriesByDay.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(DayOfWeek::getValue)))
                .map(entry -> new DailyScheduleResponse(
                        entry.getKey(),
                        entry.getValue()
                                .stream()
                                .sorted(Comparator.comparingInt(scheduleEntry -> scheduleEntry.slotType().displayOrder()))
                                .map(this::toScheduleEntryResponse)
                                .toList()
                ))
                .toList();
    }

    private ScheduleEntryResponse toScheduleEntryResponse(ScheduleEntry entry) {
        return new ScheduleEntryResponse(
                entry.slotType(),
                programContentResponseMapper.toProgramContentResponse(entry.content())
        );
    }
}