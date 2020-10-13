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
@Api(value = "Tweet fetch APIs")
@CrossOrigin(origins = "*")
public class TwitterStreamController {

    private TweetService tweetService;
    private TwitterTokenService twitterTokenService;
    private TweetNonBlockingService tweetNonBlockingService;
    Logger logger = LoggerFactory.getLogger(TwitterStreamController.class);

    @Autowired
    public TwitterStreamController(TweetService tweetService, TwitterTokenService twitterTokenService, TweetNonBlockingService tweetNonBlockingService) {
        this.tweetService = tweetService;
        this.twitterTokenService = twitterTokenService;
        this.tweetNonBlockingService = tweetNonBlockingService;
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

    @GetMapping("/token")
    @ApiOperation(value = "This API gets the access token")
    public ResponseEntity<Response> getToken() {
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {
            String token = twitterTokenService.getToken();
            if(!StringUtils.isEmpty(token)){
                response.setData(token);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode(HttpStatus.NOT_FOUND);
                response.setMessage(ApplicationConstants.FAILURE);
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ApplicationConstants.FAILURE);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/fetch/stream")
    public ResponseEntity<Response> fetchLiveStream() {
        logger.info("starting twitter stream API....");
        Response response = Response.getSuccessResponse();
        tweetNonBlockingService.getTweetsNonBlocking();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch/tweets")
    @ApiOperation(value = "This API fetches streamed tweets")
    @ResponseBody
    public ResponseEntity<Response> fetchStreamedTweets() {
        logger.info("Getting Started!! fetchStreamedTweets");
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {
            StreamedTweetsResponse tweets = tweetService.fetchStreamedTweets();
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

