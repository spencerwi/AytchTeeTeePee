# AytchTeeTeePee

A nicer, CompleteableFuture-friendly HTTPClient for dead-simple async HTTP requests.


## Usage

First, instantiate a new HTTP client:

```java
HTTP client = new HTTP();
```

Then, call one of the following:

* `client.get(String url)` -> `CompleteableFuture<Response>`
* `client.post(String url, Object body)` -> `CompleteableFuture<Response>`
* `client.put(String url, Object body)` -> `CompleteableFuture<Response>`
* `client.delete(String url)` -> `CompleteableFuture<Response>`

For both `post` and `put`, the request body object is serialized to JSON using [Google's Gson library](https://github.com/google-gson/google-gson).

`Response` objects are instances of the Response class defined in  [AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client).

For example uses, check out [the integration tests](https://github.com/spencerwi/AytchTeeTeePee/blob/master/src/integrationTests/java/com/spencerwi/aytchteeteepee/)
