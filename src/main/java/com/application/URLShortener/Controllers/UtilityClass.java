package com.application.URLShortener.Controllers;

import com.application.URLShortener.Database.URLDatabase.URL;
import com.application.URLShortener.Database.URLDatabase.URLRepository;
import com.application.URLShortener.Database.UserDatabase.User;
import com.application.URLShortener.Database.UserDatabase.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class UtilityClass {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private URLRepository urlRepository;

    static String c_defaultUrl = "https://www.google.com";

    public User createUser(String userName, String email)
    {
        long userCount = userRepository.count();
        User user = new User(userCount+1, userName, email);
        userRepository.save(user);
        return user;
    }

    public String createUrl(String originalUrl, Date expirationDate, long userId)
    {
        String uniqueUrl = originalUrl.concat(String.valueOf(userId));
        String encryptedUrl = Hashing.sha256().hashBytes(originalUrl.getBytes(StandardCharsets.UTF_8)).toString();
        URL url = new URL.URLBuilder(encryptedUrl,originalUrl,userId).setExpiration(expirationDate).build();
        urlRepository.save(url);
        return encryptedUrl;
    }

    public void deleteUrlById(String encryptedUrl)
    {
        Optional<URL> url = urlRepository.findURLByHash(encryptedUrl);
        if(!url.isPresent())
        {
            return;
        }
        urlRepository.delete(url.get());
    }

    public String getOriginalUrlFromDB(String tinyUrl)
    {
        Optional<URL> url = urlRepository.findURLByHash(tinyUrl);
        if(!url.isPresent())
        {
            return c_defaultUrl;
        }
        return url.get().getOriginalUrl();
    }
}
