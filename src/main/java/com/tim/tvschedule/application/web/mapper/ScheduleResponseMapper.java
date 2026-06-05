package com.tim.tvschedule.application.web.mapper;

import com.tim.tvschedule.application.web.dto.*;
import com.tim.tvschedule.domain.model.*;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScheduleEntryMapper {

    public static List<DailyScheduleResponse> toDailyScheduleResponse(List<ScheduleEntry> entries) {
        Map<DayOfWeek, List<ScheduleEntry>> entriesByDay = entries.stream()
                .collect(Collectors.groupingBy(ScheduleEntry::getDay));

        return entriesByDay.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparingInt(DayOfWeek::getValue)))
                .map(entry -> new DailyScheduleResponse(
                        entry.getKey(),
                        entry.getValue()
                                .stream()
                                .map(ScheduleEntryMapper::toScheduleEntryResponse)
                                .toList()
                ))
                .toList();
    }

    private static ScheduleEntryResponse toScheduleEntryResponse(ScheduleEntry entry) {
        return new ScheduleEntryResponse(
                toProgramContentResponse(entry.getContent())
        );
    }

    private static ProgramContentResponse toProgramContentResponse(ProgramContent content) {
        return new ProgramContentResponse(
                content.getTitle(),
                content.getDescription(),
                content.getStreamingPlatform()
        );
    }

    private static ProgramContentType getType(ProgramContent content) {
        if (content instanceof Movie) {
            return ProgramContentType.MOVIE;
        }

        if (content instanceof TvShow) {
            return ProgramContentType.TV_SHOW;
        }

        throw new IllegalArgumentException("Unsupported program content type: " + content.getClass().getName());
    }
}