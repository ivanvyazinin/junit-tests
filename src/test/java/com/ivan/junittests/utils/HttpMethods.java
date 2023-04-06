package com.ivan.junittests.utils;

import com.ivan.junittests.models.Creds;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class HttpMethods {

    private HttpMethods() {
        throw new IllegalStateException("Utility class");
    }

    public static Response post(String endPoint, Object content) {
        RequestSpecification request = prepareRequest()
                .contentType(ContentType.JSON)
                .body(content);

        return request
                .when()
                .post(endPoint);
    }

    public static Response put(String endPoint, Object content) {
        RequestSpecification request = prepareRequest()
                .contentType(ContentType.JSON)
                .body(content);

        return request
                .when()
                .put(endPoint);
    }

    public static Response get(String endPoint) {
        RequestSpecification request = prepareRequest();

        return request
                .when()
                .get(endPoint);
    }

    public static Response delete(String endPoint, Creds creds) {
        RequestSpecification request = prepareRequest(creds);

        return request
                .when()
                .delete(endPoint);
    }

    private static RequestSpecification prepareRequest(Creds creds) {
        String usernamePassword = creds.getUserName() + ":" + creds.getPassword();
        return prepareRequest()
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes()));
    }

    public static RequestSpecification prepareRequest() {
        return given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}
