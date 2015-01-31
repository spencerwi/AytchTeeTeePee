package com.spencerwi.aytchteeteepee;

import com.google.gson.Gson;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class HTTPTest {

    @InjectMocks private HTTP testSubject;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS) private AsyncHttpClient innerHttpClient;

    @Captor private ArgumentCaptor<AsyncCompletionHandler<Response>> responseHandlerCaptor;

    private Gson gson;
    private Map<String, Integer> requestBody;


    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        requestBody = new HashMap<>();
        requestBody.put("One", 1);
        requestBody.put("Two", 2);
        requestBody.put("Three", 3);
    }

    @Test
    public void canGet() throws Exception {
        final AsyncHttpClient.BoundRequestBuilder mockRequestBuilder = mock(AsyncHttpClient.BoundRequestBuilder.class);
        when(innerHttpClient.prepareGet("localhost"))
            .thenReturn(mockRequestBuilder);
        final Response fakeResponse = mock(Response.class);

        Consumer<Response> testResponseFlowthrough = response -> {
            assertThat(response, is(fakeResponse));
        };

        CompletableFuture<Void> futureResponse = testSubject.get("localhost")
            .thenAccept(testResponseFlowthrough);

        verify(mockRequestBuilder).execute(responseHandlerCaptor.capture());

        responseHandlerCaptor.getValue().onCompleted(fakeResponse);

    }

    @Test
    public void canPost() throws Exception {
        final AsyncHttpClient.BoundRequestBuilder mockRequestBuilder = mock(AsyncHttpClient.BoundRequestBuilder.class);
        when(innerHttpClient.preparePost("localhost").setBody(gson.toJson(requestBody)))
            .thenReturn(mockRequestBuilder);
        final Response fakeResponse = mock(Response.class);

        Consumer<Response> testResponseFlowthrough = response -> {
            assertThat(response, is(fakeResponse));
        };

        CompletableFuture<Void> futureResponse = testSubject.post("localhost", requestBody)
            .thenAccept(testResponseFlowthrough);

        verify(mockRequestBuilder).execute(responseHandlerCaptor.capture());

        responseHandlerCaptor.getValue().onCompleted(fakeResponse);
    }

    @Test
    public void canPut() throws Exception {
        final AsyncHttpClient.BoundRequestBuilder mockRequestBuilder = mock(AsyncHttpClient.BoundRequestBuilder.class);
        when(innerHttpClient.preparePut("localhost").setBody(gson.toJson(requestBody)))
            .thenReturn(mockRequestBuilder);
        final Response fakeResponse = mock(Response.class);

        Consumer<Response> testResponseFlowthrough = response -> {
            assertThat(response, is(fakeResponse));
        };

        CompletableFuture<Void> futureResponse = testSubject.put("localhost", requestBody)
            .thenAccept(testResponseFlowthrough);

        verify(mockRequestBuilder).execute(responseHandlerCaptor.capture());

        responseHandlerCaptor.getValue().onCompleted(fakeResponse);
    }

    @Test
    public void canDelete() throws Exception {
        final AsyncHttpClient.BoundRequestBuilder mockRequestBuilder = mock(AsyncHttpClient.BoundRequestBuilder.class);
        when(innerHttpClient.prepareDelete("localhost"))
            .thenReturn(mockRequestBuilder);
        final Response fakeResponse = mock(Response.class);

        Consumer<Response> testResponseFlowthrough = response -> {
            assertThat(response, is(fakeResponse));
        };

        CompletableFuture<Void> futureResponse = testSubject.delete("localhost")
            .thenAccept(testResponseFlowthrough);

        verify(mockRequestBuilder).execute(responseHandlerCaptor.capture());

        responseHandlerCaptor.getValue().onCompleted(fakeResponse);
    }
}