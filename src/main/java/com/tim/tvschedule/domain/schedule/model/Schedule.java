package com.tim.tvschedule.domain.schedule.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "schedules")
public record Schedule(

        ObjectId id,
        LocalDate weekStartTime,
        String prompt,
        String description,
        List<ScheduleDay> scheduleDays,
        Instant createdAt,
        Instant updatedAt
) { }
