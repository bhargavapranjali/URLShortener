package com.application.URLShortener.Database.URLDatabase;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface URLRepository extends MongoRepository<URL,String> {
    @Query("{urlHash:'?0'}")
    Optional<URL> findURLByHash(String urlHash);
}
