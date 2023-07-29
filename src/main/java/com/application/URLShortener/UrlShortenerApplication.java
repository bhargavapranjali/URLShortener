package com.application.URLShortener;

import com.application.URLShortener.Database.URLDatabase.URLRepository;
import com.application.URLShortener.Database.UserDatabase.UserRepository;
import com.mongodb.event.CommandListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories
public class UrlShortenerApplication implements CommandListener {

	@Autowired
	UserRepository userRepository;

	@Autowired
	URLRepository urlRepository;

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}
