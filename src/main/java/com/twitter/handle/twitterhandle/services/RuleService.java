package com.twitter.handle.twitterhandle.services;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.request.AddRulesRequest;
import com.twitter.handle.twitterhandle.request.TwitterRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RuleService {
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TweetService.class);

    @Autowired
    private RuleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean addRules(AddRulesRequest request) {
        ResponseEntity<String> response = null;
        logger.info("request::{}", request.toString());
        Boolean created = false;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add(ApplicationConstants.AUTHORIZATION_KEY, "Bearer AAAAAAAAAAAAAAAAAAAAAMKmIAEAAAAAjHF%2FjxxNcIM3pn16zZFKFA2QnEI%3Dgtb6Eo595mEnLMKEGuPRSQjY9dSQXwUWD0NAcbLMUkwRXvIKr4");
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            Map<String, Object> jsonObject = new HashMap<>();
            jsonObject.put("add", request);
            HttpEntity<?> entity = new HttpEntity<Object>(request, headers);
            response = restTemplate.exchange(ApplicationConstants.TWITTER_RULE_END_POINT, HttpMethod.POST, entity, String.class);
            logger.info("Response status code::{}", response.getStatusCode());
            if (response.getStatusCode() == HttpStatus.CREATED) {
                created = true;
            } else {
                logger.warn("failed to create the rules::{}", request);
            }
        } catch (RestClientException e) {
            logger.error("RestClientException at {{}}", e.getStackTrace()[0].getLineNumber(), e);
        } catch (Exception e) {
            logger.error("Exception at {{}}", e.getStackTrace()[0].getLineNumber(), e);
        }
        return created;
    }

    public AddRulesRequest createRuleObject(String value) {
        List<TwitterRule> rules = new ArrayList<>();
        TwitterRule rule = new TwitterRule(value, value);
        rules.add(rule);
        return new AddRulesRequest(rules);
    }
}
