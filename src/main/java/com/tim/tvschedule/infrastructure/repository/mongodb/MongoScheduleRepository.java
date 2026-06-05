package com.tim.tvschedule.infrastructure.repository.mongodb;

import com.tim.tvschedule.domain.model.ScheduleEntry;
import com.tim.tvschedule.domain.repository.ScheduleRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoScheduleRepository implements ScheduleRepository {

    private static final String COLLECTION_NAME = "schedule_entries";

    private final MongoTemplate mongoTemplate;

    public MongoScheduleRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void clear() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    @Override
    public void saveAll(List<ScheduleEntry> scheduleEntries) {
        for (ScheduleEntry scheduleEntry : scheduleEntries) {
            mongoTemplate.save(scheduleEntry, COLLECTION_NAME);
        }
    }

    @Override
    public List<ScheduleEntry> findAll() {
        return mongoTemplate.findAll(ScheduleEntry.class, COLLECTION_NAME);
    }
}