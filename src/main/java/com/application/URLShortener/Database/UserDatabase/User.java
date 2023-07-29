package com.application.URLShortener.Database.UserDatabase;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    private long userId;
    private String name, email;

    public User(long userId, String name, String email)
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
