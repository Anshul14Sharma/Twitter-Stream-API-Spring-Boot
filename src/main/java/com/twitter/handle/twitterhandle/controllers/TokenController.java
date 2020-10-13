package com.twitter.handle.twitterhandle.controllers;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import com.twitter.handle.twitterhandle.response.Response;
import com.twitter.handle.twitterhandle.services.TwitterTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Twitter access token fetch APIs")
@CrossOrigin(origins = "*")
public class TokenController {

    private TwitterTokenService twitterTokenService;

    @Autowired
    public TokenController(TwitterTokenService twitterTokenService) {
        this.twitterTokenService = twitterTokenService;
    }


    @GetMapping("/token")
    @ApiOperation(value = "This API gets the access token")
    public ResponseEntity<Response> getToken() {
        ResponseEntity<Response> responseEntity = null;
        Response response = Response.getSuccessResponse();
        try {
            String token = twitterTokenService.getToken();
            if (!StringUtils.isEmpty(token)) {
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
}
