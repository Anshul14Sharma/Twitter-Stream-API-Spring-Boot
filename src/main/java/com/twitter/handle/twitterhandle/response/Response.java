package com.twitter.handle.twitterhandle.response;

import com.twitter.handle.twitterhandle.constants.ApplicationConstants;
import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus statusCode;
    private String message;
    private Object data;

    public Response(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Response getSuccessResponse(){
        return new Response(HttpStatus.OK, ApplicationConstants.SUCCESS);
    }
}
