package com.spencerwi.aytchteeteepee;

import com.ning.http.client.Response;
import com.spencerwi.aytchteeteepee.aytchteeteepee.HTTP;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class HTTPIntegrationTest {


    private HTTP http;

    @Before
    public void setUp() throws Exception {
        Assume.assumeThat("http://httpbin.org must be reachable", this.canReachTestSite("http://httpbin.org"), is(true));
        http = new HTTP();
    }

    public Optional<String> unwrapResponseBody(Response response){
        try {
            return Optional.ofNullable(response.getResponseBody());
        } catch(IOException e) {
            return Optional.empty();
        }
    }

    @Test
    public void sendLiveFireGet(){
        http.get("http://httpbin.org/get")
            .thenApply(this::unwrapResponseBody).thenAccept(
            responseBody -> assertThat(responseBody.isPresent(), is(true))
        );

    }

    @Test
    public void sendLiveFirePost(){
        Map<String, Integer> testPostObject = new HashMap<>();
        testPostObject.put("One", 1);
        testPostObject.put("Two", 2);
        testPostObject.put("Three", 3);
        http.post("http://httpbin.org/post", testPostObject)
            .thenApply(this::unwrapResponseBody)
            .thenAccept(responseBody -> {
                            assertThat(responseBody.isPresent(), is(true));

                            String responseBodyString = responseBody.get();
                            assertThat(responseBodyString, containsString("\"One\": 1"));
                            assertThat(responseBodyString, containsString("\"Two\": 2"));
                            assertThat(responseBodyString, containsString("\"Three\": 3"));
                        });
    }

    @Test
    public void sendLiveFirePut(){
        Map<String, Integer> testPutObject = new HashMap<>();
        testPutObject.put("One", 1);
        testPutObject.put("Two", 2);
        testPutObject.put("Three", 3);
        http.put("http://httpbin.org/put", testPutObject)
            .thenApply(this::unwrapResponseBody)
            .thenAccept(responseBody -> {
                            assertThat(responseBody.isPresent(), is(true));

                            String responseBodyString = responseBody.get();
                            assertThat(responseBodyString, containsString("\"One\": 1"));
                            assertThat(responseBodyString, containsString("\"Two\": 2"));
                            assertThat(responseBodyString, containsString("\"Three\": 3"));
                        });
    }

    @Test
    public void sendLiveFireDelete(){
        http.delete("http://httpbin.org/delete")
            .thenApply(this::unwrapResponseBody).thenAccept(
            responseBody -> assertThat(responseBody.isPresent(), is(true))
        );
    }

    private boolean canReachTestSite(String testSiteURL){
        try {
            URL url = new URL(testSiteURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Object objData = urlConnection.getContent();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
