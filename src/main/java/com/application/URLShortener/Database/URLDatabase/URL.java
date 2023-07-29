package com.application.URLShortener.Database.URLDatabase;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "URL")
public class URL {

    private String urlHash;
    private String originalURL;
    private Date creationDate, expirationDate;
    private long userId;
    public static class URLBuilder{
        private String urlHash;
        private String originalURL;
        private Date creationDate, expirationDate;
        private long userId;

        //set required parameters
        public URLBuilder(String urlHash, String originalURL, long userId)
        {
            this.urlHash = urlHash;
            this.originalURL = originalURL;
            this.creationDate = new Date();
            this.userId = userId;
        }

        //set optional parameters
        public URLBuilder setExpiration(Date expirationDate)
        {
            Calendar cal=Calendar.getInstance();
            cal.setTime(this.creationDate);
            cal.set(Calendar.YEAR,this.creationDate.getYear()+5);
            Date dateAfter = cal.getTime();

            if(expirationDate == null)
            {
                this.expirationDate = dateAfter;
            }
            else {
                this.expirationDate = expirationDate.before(dateAfter) ? expirationDate : dateAfter;
            }
            return this;
        }

        //once the object of type URLDatabaseBuilder is created, assign attributes to URLBuilder back
        public URL build()
        {
            return new URL(this);
        }
    }
    public URL(URLBuilder urlDatabaseBuilder)
    {
        this.urlHash = urlDatabaseBuilder.urlHash;
        this.originalURL = urlDatabaseBuilder.originalURL;
        this.creationDate = urlDatabaseBuilder.creationDate;
        this.expirationDate = urlDatabaseBuilder.expirationDate;
        this.userId = urlDatabaseBuilder.userId;
    }

    public String getOriginalUrl()
    {
        return this.originalURL;
    }
}

