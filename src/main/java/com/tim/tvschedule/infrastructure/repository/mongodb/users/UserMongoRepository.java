package com.tim.tvschedule.infrastructure.repository.mongodb.users;

import com.tim.tvschedule.infrastructure.repository.mongodb.users.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByEmail(String email);
}