package com.afalenkin.oauth.repository;

import com.afalenkin.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'account.userName': ?0}")
    Optional<User> findByUserName(String userName);
}
