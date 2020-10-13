package com.twitter.handle.twitterhandle.controllers;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Twitter rules actions APIs")
@CrossOrigin(origins = "*")
public class RulesController {

    Logger logger = LoggerFactory.getLogger(TwitterStreamController.class);

    @GetMapping("/add/tweet/rule/{value}")
    @ApiOperation(value = "This API fetches tweets")
    @ResponseBody
    public ResponseEntity<Response> addRule(@PathVariable String value) {
        logger.trace("Getting Started!!");
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {

        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ApplicationConstants.FAILURE);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
