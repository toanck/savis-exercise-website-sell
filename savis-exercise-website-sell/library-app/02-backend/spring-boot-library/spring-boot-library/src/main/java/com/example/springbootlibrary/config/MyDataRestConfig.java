package com.example.springbootlibrary.config;

import com.example.springbootlibrary.entity.Book;
import com.example.springbootlibrary.entity.Message;
import com.example.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:3000";

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors){
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Message.class);

        disableHttpMethod(Book.class,config, theUnsupportedActions);
        disableHttpMethod(Review.class,config,theUnsupportedActions);
        disableHttpMethod(Message.class,config,theUnsupportedActions);

        // configure CORS mapping
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethod(Class theClass,
                                   RepositoryRestConfiguration config,
                                   HttpMethod[] theUnsupportedActions){

        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata,httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }

}
