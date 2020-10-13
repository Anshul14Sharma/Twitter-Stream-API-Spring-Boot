package com.twitter.handle.twitterhandle;

import com.twitter.handle.twitterhandle.controllers.TwitterStreamController;
import com.twitter.handle.twitterhandle.services.TweetNonBlockingService;
import com.twitter.handle.twitterhandle.services.TweetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TwitterStreamController.class)
public class TwitterStreamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TweetService tweetService;
    @MockBean
    private TweetNonBlockingService tweetNonBlockingService;

    @Test
    public void whenInValid_thenReturns404() throws Exception {
        mockMvc.perform(get("/fetch/tweets")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

}
