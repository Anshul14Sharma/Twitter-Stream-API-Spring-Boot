package com.twitter.handle.twitterhandle.model;

import javax.persistence.*;

@Entity
@Table(name="tweets")
public class TwitterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "INT")
    private Integer id;

    @Column(name = "tweet_id")
    private String tweetId;

    @Column(name = "text")
    private String text;

    public TwitterEntity() {
    }

    public TwitterEntity(String tweetId, String text) {
        this.tweetId = tweetId;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
