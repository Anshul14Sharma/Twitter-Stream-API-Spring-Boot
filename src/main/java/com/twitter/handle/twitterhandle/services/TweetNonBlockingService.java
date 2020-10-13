package com.twitter.handle.twitterhandle.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.model.Tweet;
import com.twitter.handle.twitterhandle.model.TwitterEntity;
import com.twitter.handle.twitterhandle.repository.TweetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class TweetNonBlockingService {
    private static final Logger logger = LoggerFactory.getLogger(TweetService.class);
    @Autowired
    TweetsRepository repository;

    public void getTweetsNonBlocking() {
        logger.info("Starting getTweetsNonBlocking!");
        Flux<String> tweetFlux = WebClient.create()
                .get()
                .uri(ApplicationConstants.TWITTER_STREAMING_END_POINT)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(ApplicationConstants.AUTHORIZATION_KEY, "Bearer AAAAAAAAAAAAAAAAAAAAAMKmIAEAAAAAjHF%2FjxxNcIM3pn16zZFKFA2QnEI%3Dgtb6Eo595mEnLMKEGuPRSQjY9dSQXwUWD0NAcbLMUkwRXvIKr4")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class);
        tweetFlux.subscribe(tweet -> {
            logger.info(tweet);
            if (!StringUtils.isEmpty(tweet)) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode json = objectMapper.readTree(tweet);
                    Tweet streamedTweet = objectMapper.readValue(json.get("data").toString(), Tweet.class);
                    TwitterEntity entity = new TwitterEntity(streamedTweet.getId(), streamedTweet.getText());
                    repository.save(entity);
                } catch (JsonProcessingException e) {
                    logger.info("JsonProcessingException :: {{}}", e.getStackTrace()[0].getLineNumber(), e);
                } catch (Exception e) {
                    logger.error("Exception at {{}}", e.getStackTrace()[0].getLineNumber(), e);
                }
            } else {
                logger.info("no data for stream");
            }
        });
        logger.info("Exiting getTweetsNonBlocking!");
    }
}
