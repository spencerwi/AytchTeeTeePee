package com.spencerwi.aytchteeteepee.aytchteeteepee;

import com.google.gson.Gson;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.util.concurrent.CompletableFuture;

public final class HTTP {

    private AsyncHttpClient asyncHttpClient;
    private Gson gsonSerializer;

    public HTTP() {
        this.asyncHttpClient = new AsyncHttpClient();
        this.gsonSerializer = new Gson();
    }

    public CompletableFuture<Response> get(String url){
        final CompletableFuture<Response> future = new CompletableFuture<Response>();
        this.asyncHttpClient.prepareGet(url).execute(new AsyncCompletionHandler<Response>() {
                                                         @Override
                                                         public Response onCompleted(Response response) throws Exception {
                                                             future.complete(response);
                                                             return response;
                                                         }

                                                         @Override
                                                         public void onThrowable(Throwable t) {
                                                             future.cancel(true);
                                                         }
                                                     });
        return future;
    }

    public CompletableFuture<Response> post(String url, Object body){
        final CompletableFuture<Response> future = new CompletableFuture<Response>();
        this.asyncHttpClient.preparePost(url)
                            .setBody(this.gsonSerializer.toJson(body))
                            .execute(new AsyncCompletionHandler<Response>() {
                                         @Override
                                         public Response onCompleted(Response response) throws Exception {
                                             future.complete(response);
                                             return response;
                                         }

                                         @Override
                                         public void onThrowable(Throwable t) {
                                             future.cancel(true);
                                         }
                                     }
                            );
        return future;
    }

    public CompletableFuture<Response> put(String url, Object body){
        final CompletableFuture<Response> future = new CompletableFuture<Response>();
        this.asyncHttpClient.preparePut(url)
                            .setBody(this.gsonSerializer.toJson(body))
                            .execute(new AsyncCompletionHandler<Response>() {
                                         @Override
                                         public Response onCompleted(Response response) throws Exception {
                                             future.complete(response);
                                             return response;
                                         }

                                         @Override
                                         public void onThrowable(Throwable t) {
                                             future.cancel(true);
                                         }
                                     });
        return future;
    }

    public CompletableFuture<Response> delete(String url){
        final CompletableFuture<Response> future = new CompletableFuture<Response>();
        this.asyncHttpClient.prepareDelete(url).execute(new AsyncCompletionHandler<Response>() {
                                                         @Override
                                                         public Response onCompleted(Response response) throws Exception {
                                                             future.complete(response);
                                                             return response;
                                                         }

                                                         @Override
                                                         public void onThrowable(Throwable t) {
                                                             future.cancel(true);
                                                         }
                                                     });
        return future;
    }
}
