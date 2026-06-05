package com.tim.tvschedule.infrastructure.repository.mongodb;

import com.tim.tvschedule.domain.model.Movie;
import com.tim.tvschedule.domain.model.ProgramContent;
import com.tim.tvschedule.domain.model.ProgramContentType;
import com.tim.tvschedule.domain.model.TvShow;
import com.tim.tvschedule.domain.repository.ProgramRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MongoProgramRepository implements ProgramRepository {

    private static final String COLLECTION_NAME = "program_content";

    private final MongoTemplate mongoTemplate;

    public MongoProgramRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ProgramContent> findAll() {
        List<ProgramContent> programs = new ArrayList<>();
        programs.addAll(findAllMovies());
        programs.addAll(findAllTvShows());
        return programs;
    }

    @Override
    public List<ProgramContent> findByType(ProgramContentType type) {
        return List.of();
    }

    @Override
    public List<Movie> findAllMovies() {
        Query query = Query.query(Criteria.where("type").is(ProgramContentType.MOVIE));
        return mongoTemplate.find(query, Movie.class, COLLECTION_NAME);
    }

    @Override
    public List<TvShow> findAllTvShows() {
        Query query = Query.query(Criteria.where("type").is(ProgramContentType.TV_SHOW));
        return mongoTemplate.find(query, TvShow.class, COLLECTION_NAME);
    }

    @Override
    public void saveAll(List<ProgramContent> programs) {
        for (ProgramContent program : programs) {
            mongoTemplate.save(program, COLLECTION_NAME);
        }
    }

    @Override
    public void deleteAll() {
        mongoTemplate.remove(new Query(), COLLECTION_NAME);
    }
}