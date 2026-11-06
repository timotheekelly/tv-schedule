package com.tim.tvschedule.infrastructure.repository.mongodb;

import com.tim.tvschedule.domain.schedule.model.Schedule;
import com.tim.tvschedule.domain.schedule.repository.ScheduleRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoScheduleRepository implements ScheduleRepository {

    private static final String COLLECTION_NAME = "schedules";

    private final MongoTemplate mongoTemplate;

    public MongoScheduleRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void clear() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Override
    public void save(Schedule scheduleEntry) {
        mongoTemplate.save(scheduleEntry, COLLECTION_NAME);
    }

    @Override
    public void saveAll(List<Schedule> scheduleEntries) {
    }

    @Override
    public List<Schedule> findAll() {
        return List.of();
    }


}