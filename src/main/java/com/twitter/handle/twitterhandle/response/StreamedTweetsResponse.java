package com.twitter.handle.twitterhandle.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twitter.handle.twitterhandle.model.TwitterEntity;

import java.util.List;

public class StreamedTweetsResponse {
    @JsonProperty(value = "totalCount")
    private Integer totalCount;
    @JsonProperty(value = "data")
    List<TwitterEntity> tweets;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<TwitterEntity> getTweets() {
        return tweets;
    }

    public void setTweets(List<TwitterEntity> tweets) {
        this.tweets = tweets;
    }
}
