package com.twitter.handle.twitterhandle;

import com.twitter.handle.twitterhandle.repository.TweetsRepository;
import com.twitter.handle.twitterhandle.services.TweetService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

@SpringBootTest
class TwitterHandleApplicationTests {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private TweetsRepository repository;
	@InjectMocks
	private TweetService tweetService = new TweetService(restTemplate,  repository);

	@Before
	public void beforeTest() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	void check_Fetch_Streamed_Tweets_When_list_is_null() {
		Mockito.doReturn(null).when(repository).findAll(Mockito.any(Sort.class));
		assertTrue(Objects.isNull(tweetService.fetchStreamedTweets()));
	}

	@Test
	void check_Fetch_Streamed_Tweets_When_list_is_not_null() {
		Mockito.doReturn(new ArrayList<>()).when(repository).findAll();
		assertTrue(Objects.nonNull(tweetService.fetchStreamedTweets()));
	}


}
