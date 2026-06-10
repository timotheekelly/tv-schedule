package com.tim.tvschedule.infrastructure.repository.mongodb.program;

import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.TvShow;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoProgramRepository implements ProgramRepository {

    private static final String COLLECTION_NAME = "program_content";

    private final MongoTemplate mongoTemplate;

    public MongoProgramRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ProgramContent> findAll() {
        return mongoTemplate.find(new Query(), Document.class, COLLECTION_NAME)
                .stream()
                .map(this::toProgramContent)
                .toList();
    }

    @Override
    public List<ProgramContent> findByType(ProgramContentType type) {
        if (type == null) {
            return findAll();
        }

        Query query = Query.query(Criteria.where("type").is(type));

        return mongoTemplate.find(query, Document.class, COLLECTION_NAME)
                .stream()
                .map(this::toProgramContent)
                .toList();
    }

    @Override
    public Optional<ProgramContent> findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));

        Document document = mongoTemplate.findOne(
                query,
                Document.class,
                COLLECTION_NAME
        );

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(toProgramContent(document));
    }

    @Override
    public ProgramContent save(ProgramContent program) {
        mongoTemplate.save(program, COLLECTION_NAME);
        return program;
    }

    @Override
    public void saveAll(List<ProgramContent> programs) {
        for (ProgramContent program : programs) {
            save(program);
        }
    }

    @Override
    public void deleteById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, COLLECTION_NAME);
    }

    @Override
    public void deleteAll() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }

    private ProgramContent toProgramContent(Document document) {
        String rawType = document.getString("type");

        if (rawType == null || rawType.isBlank()) {
            throw new IllegalStateException(
                    "Program content document is missing type: " + document.toJson()
            );
        }

        ProgramContentType type = ProgramContentType.valueOf(rawType);

        return switch (type) {
            case MOVIE -> mongoTemplate.getConverter().read(Movie.class, document);
            case TV_SHOW -> mongoTemplate.getConverter().read(TvShow.class, document);
        };
    }
}