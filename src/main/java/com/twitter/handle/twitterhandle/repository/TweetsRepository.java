package com.twitter.handle.twitterhandle.repository;

import com.twitter.handle.twitterhandle.model.Tweet;
import com.twitter.handle.twitterhandle.model.TwitterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetsRepository extends JpaRepository<TwitterEntity, Integer> {

}
