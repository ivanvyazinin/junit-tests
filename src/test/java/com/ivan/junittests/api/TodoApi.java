package com.ivan.junittests.api;

import com.ivan.junittests.config.TodoConfig;
import com.ivan.junittests.models.Creds;
import com.ivan.junittests.models.Todo;
import io.restassured.response.Response;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ivan.junittests.config.Configuration.getTodoConfig;
import static com.ivan.junittests.utils.HttpMethods.*;

public class TodoApi {
    private TodoConfig config = getTodoConfig();

    private final String TODOS_ENDPOINT = "/todos";

    public Response createTodo(Todo body) {
        return post(config.getHost() + TODOS_ENDPOINT, body);
    }

    public Response updateTodo(BigInteger id, Todo body) {
        return put(config.getHost() + TODOS_ENDPOINT + "/" + id, body);
    }

    public Response getTodo() {
        return get(config.getHost() + TODOS_ENDPOINT);
    }

    public Response getTodo(Map<String, Integer> params) {
        String queryParams = (params != null) ? params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&")) : "";

        String endpointUrl = config.getHost() + TODOS_ENDPOINT;
        if (!queryParams.isEmpty()) {
            endpointUrl += "?" + queryParams;
        }

        return get(endpointUrl);    }

    public Response deleteTodo(BigInteger id, Creds creds) {
        return delete(config.getHost() + TODOS_ENDPOINT + "/" + id, creds);
    }
}
