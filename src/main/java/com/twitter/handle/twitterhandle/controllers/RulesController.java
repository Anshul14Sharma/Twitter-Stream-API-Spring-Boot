package com.twitter.handle.twitterhandle.controllers;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.request.AddRulesRequest;
import com.twitter.handle.twitterhandle.request.TwitterRule;
import com.twitter.handle.twitterhandle.response.Response;
import com.twitter.handle.twitterhandle.services.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Twitter rules actions APIs")
@CrossOrigin(origins = "*")
public class RulesController {

    private RuleService ruleService;

    Logger logger = LoggerFactory.getLogger(RulesController.class);

    @Autowired
    private RulesController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/add/tweet/rule/{value}")
    @ApiOperation(value = "This API fetches tweets")
    @ResponseBody
    public ResponseEntity<Response> addRule(@PathVariable String value) {
        logger.info("addRule ::{}", value);
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {
            AddRulesRequest addRulesRequest = ruleService.createRuleObject(value);
            if (ruleService.addRules(addRulesRequest)) {
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(ApplicationConstants.FAILURE);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
