package com.twitter.handle.twitterhandle.controllers;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.request.AddRulesRequest;
import com.twitter.handle.twitterhandle.response.Response;
import com.twitter.handle.twitterhandle.response.StreamedTweetsResponse;
import com.twitter.handle.twitterhandle.services.RuleService;
import com.twitter.handle.twitterhandle.services.TweetNonBlockingService;
import com.twitter.handle.twitterhandle.services.TweetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Api(value = "Tweet fetch APIs")
@CrossOrigin(origins = "*")
public class TwitterStreamController {

    private TweetService tweetService;
    private TweetNonBlockingService tweetNonBlockingService;
    private RuleService ruleService;
    Logger logger = LoggerFactory.getLogger(TwitterStreamController.class);

    @Autowired
    public TwitterStreamController(TweetService tweetService, TweetNonBlockingService tweetNonBlockingService, RuleService ruleService) {
        this.tweetService = tweetService;
        this.tweetNonBlockingService = tweetNonBlockingService;
        this.ruleService = ruleService;
    }

    @GetMapping("/fetch/stream/{value}")
    public ResponseEntity<Response> fetchLiveStream(@PathVariable String value) {
        logger.info("starting twitter stream API....");
        Response response = Response.getSuccessResponse();
        AddRulesRequest addRulesRequest = ruleService.createRuleObject(value);
        boolean created = ruleService.addRules(addRulesRequest);
        if (created) {
            tweetNonBlockingService.startTwitterStream();
        } else {
            logger.warn("failed to create the rule");
        }
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
            if (Objects.nonNull(tweets)) {
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
        return responseEntity;
    }
}

