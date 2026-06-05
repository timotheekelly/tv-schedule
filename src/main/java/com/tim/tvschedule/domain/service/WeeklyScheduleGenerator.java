package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.ScheduleEntry;
import com.tim.tvschedule.domain.model.ScheduleSlotType;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WeeklyScheduleGenerator {

    private static final List<DayOfWeek> DAYS_OF_WEEK = List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    );

    public List<ScheduleEntry> generate(List<ProgramContent> programs) {
        List<ProgramContent> movies = shuffledProgramsOfType(programs, ProgramContentType.MOVIE);
        List<ProgramContent> tvShows = shuffledProgramsOfType(programs, ProgramContentType.TV_SHOW);

        requireAtLeastOne(movies, "At least 1 movie is required to generate a weekly schedule.");
        requireAtLeastOne(tvShows, "At least 1 TV show is required to generate a weekly schedule.");

        List<ScheduleEntry> scheduleEntries = new ArrayList<>();

        for (int dayIndex = 0; dayIndex < DAYS_OF_WEEK.size(); dayIndex++) {
            DayOfWeek day = DAYS_OF_WEEK.get(dayIndex);

            ProgramContent movie = getLooped(movies, dayIndex);
            ProgramContent primaryTvShow = getLooped(tvShows, dayIndex * 2);
            ProgramContent secondaryTvShow = getLooped(tvShows, dayIndex * 2 + 1);

            scheduleEntries.add(createScheduleEntry(day, ScheduleSlotType.FEATURE_MOVIE, movie));
            scheduleEntries.add(createScheduleEntry(day, ScheduleSlotType.TV_SHOW_PRIMARY, primaryTvShow));
            scheduleEntries.add(createScheduleEntry(day, ScheduleSlotType.TV_SHOW_SECONDARY, secondaryTvShow));
        }

        return scheduleEntries;
    }

    private List<ProgramContent> shuffledProgramsOfType(
            List<ProgramContent> programs,
            ProgramContentType type
    ) {
        List<ProgramContent> matchingPrograms = new ArrayList<>(
                programs.stream()
                        .filter(program -> program.getType() == type)
                        .toList()
        );

        Collections.shuffle(matchingPrograms);

        return matchingPrograms;
    }

    private void requireAtLeastOne(List<ProgramContent> programs, String message) {
        if (programs.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    private ProgramContent getLooped(List<ProgramContent> programs, int index) {
        return programs.get(index % programs.size());
    }

    private ScheduleEntry createScheduleEntry(
            DayOfWeek day,
            ScheduleSlotType slotType,
            ProgramContent content
    ) {
        String id = "schedule-%s-%s".formatted(
                day.name().toLowerCase(),
                slotType.name().toLowerCase().replace("_", "-")
        );

        return new ScheduleEntry(
                id,
                day,
                slotType,
                content
        );
    }
}