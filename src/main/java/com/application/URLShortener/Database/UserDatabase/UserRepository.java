package com.application.URLShortener.Database.UserDatabase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    @Query("{email:'?0'}")
    User findByEmail(String email);
}
