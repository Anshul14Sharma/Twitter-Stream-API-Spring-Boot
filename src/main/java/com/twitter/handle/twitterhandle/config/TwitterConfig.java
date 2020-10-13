package com.twitter.handle.twitterhandle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterConfig {

    @Value("${spring.social.twitter.appId}")
    private String consumerKey;
    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;
    @Value("${twitter.access.token}")
    private String accessToken;
    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;
    @Value("${twitter.query.maxresult}")
    private String maxResult;

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public String getMaxResult() {
        return maxResult;
    }
}