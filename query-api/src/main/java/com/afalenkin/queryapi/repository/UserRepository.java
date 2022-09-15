package com.afalenkin.queryapi.repository;

import com.afalenkin.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserRepository extends MongoRepository<User, String> {
}
