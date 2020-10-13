package com.twitter.handle.twitterhandle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    @JsonProperty(value = "id")
    protected String id;
    @JsonProperty(value = "text")
    protected String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
