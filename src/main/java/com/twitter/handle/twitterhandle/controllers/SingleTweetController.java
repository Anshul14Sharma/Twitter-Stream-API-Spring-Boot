package com.twitter.handle.twitterhandle.controllers;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.model.Tweet;
import com.twitter.handle.twitterhandle.response.Response;
import com.twitter.handle.twitterhandle.response.StreamedTweetsResponse;
import com.twitter.handle.twitterhandle.response.TweetsWrapper;
import com.twitter.handle.twitterhandle.services.TweetNonBlockingService;
import com.twitter.handle.twitterhandle.services.TweetService;
import com.twitter.handle.twitterhandle.services.TwitterTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Api(value = "Single Tweet fetch APIs")
@CrossOrigin(origins = "*")
public class SingleTweetController {

    private TweetService tweetService;
    private TwitterTokenService twitterTokenService;

    Logger logger = LoggerFactory.getLogger(TwitterStreamController.class);

    @Autowired
    public SingleTweetController(TweetService tweetService, TwitterTokenService twitterTokenService) {
        this.tweetService = tweetService;
        this.twitterTokenService = twitterTokenService;
    }

    @GetMapping("/fetch/tweets/{id}")
    @ApiOperation(value = "This API fetches recent tweets")
    @ResponseBody
    public ResponseEntity<Response> fetchRecentTweets(@PathVariable String id) {
        logger.trace("Getting Started!!");
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {
            TweetsWrapper tweets = tweetService.getTweetsByQuery(id);
            if(Objects.nonNull(tweets)){
                response.setData(tweets);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND);
                response.setMessage("no data found");
                response.setData(null);
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ApplicationConstants.FAILURE);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }




}

