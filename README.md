# Twitter-Stream-API-Spring-Boot
Spring boot version:- 2.3.4.RELEASE  

This Application uses twitter stream API for access tweets based on the rules added.  
Using Spring WebClient to access the twitter stream API.  
Twitter Stream API :- https://api.twitter.com/2/tweets/search/stream?  
Rules API :- https://api.twitter.com/2/tweets/search/stream/rules  

This uses MySQL Database for persisting the contionous streaming tweets. DB name - twitter Table - tweets  
Attached script:- [tweets.sql](https://github.com/Anshul14Sharma/Twitter-Stream-API-Spring-Boot/blob/main/tweets.sql)

If you are running as the standalone application run the application using:-  
```mvn sprint-boot:run```  
Application will start at http://localhost:8080  

Swagger Url:- http://localhost:8080/swagger-ui.html

Clone the other project name [twitter-tweets-fetch-react](https://github.com/Anshul14Sharma/twitter-tweets-fetch-react)
