package com.afalenkin.queryapi.repository;

import com.afalenkin.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'$or' : [" +
            "{'firstName' : {$regex : ?0, $options : '1'}}, " +
            "{'lastName' : {$regex : ?0, $options : '1'}}, " +
            "{'email' : {$regex : ?0, $options : '1'}}, " +
            "{'account.userName' : {$regex : ?0, $options : '1'}}, " +
            "]}")
    List<User> findFiltered(String filter);
}
