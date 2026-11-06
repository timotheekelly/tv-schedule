package com.tim.tvschedule.domain.service;

import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.schedule.model.Schedule;
import com.tim.tvschedule.domain.schedule.model.ScheduleDay;
import com.tim.tvschedule.domain.schedule.repository.ScheduleRepository;
import com.tim.tvschedule.recomendation.service.MovieSelectionService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MovieSelectionService movieSelectionService;

    public ScheduleService(ScheduleRepository scheduleRepository, MovieSelectionService movieSelectionService) {
        this.scheduleRepository = scheduleRepository;
        this.movieSelectionService = movieSelectionService;
    }

    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    public List<ProgramContent> createSchedule(String prompt) {
        LocalDate startOfWeek = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // take the prompt and search movies
        // for the week, get a list of movies (7) and list of tv shows (14) that match the brief
        // assign each to a day
        // generate a description for the week from the prompt and movies. Max 3 lines and use the programmes to tell a story

        List<ProgramContent> movies = movieSelectionService.selectMovies(prompt, 7);
        List<ProgramContent> tvShows = null;

        List<ScheduleDay> dailyLineUps = new ArrayList<>();

        LocalDate dateCounter = startOfWeek;

        for (int i = 0; i < 7; i++) {
            String movieId = movies.get(i).getId();
            List<String> movieIds = new ArrayList<>();
            movieIds.add(movieId);

            ScheduleDay lineup = new ScheduleDay(startOfWeek.plusDays(i), movieIds, null);
        }

        Schedule newEntry = new Schedule(
          new ObjectId(),
          startOfWeek,
          prompt,
          null,
        null,
                Instant.now(),
                Instant.now()
        );

        return movies;
    }
}