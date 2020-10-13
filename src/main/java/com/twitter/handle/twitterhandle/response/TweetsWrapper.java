package com.twitter.handle.twitterhandle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twitter.handle.twitterhandle.model.Tweet;

import java.util.List;

public class TweetsWrapper {
    @JsonProperty(value = "totalCount")
    private Integer totalCount;
    @JsonProperty(value = "data")
    List<Tweet> tweets;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
