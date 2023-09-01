package org.praktikum.requests.constants;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestUrls {
    private final String URL = "http://qa-scooter.praktikum-services.ru";
    private final String COURIER_URL = "/api/v1/courier";
    private final String LOGIN_COURIER = "/api/v1/courier/login";
    private final String ORDERS_URL = "/api/v1/orders";
    private final String ACCEPT_ORDER = "/api/v1/orders/accept/";
    private final String GET_ORDER = "/api/v1/orders/track";

    public String getURL() {
        return URL;
    }
//    public String getCOURIER_URL() {
//        return COURIER_URL;
//    }

    public String getCOURIER_URL() {
        return COURIER_URL;
    }

    public String getLOGIN_COURIER() {
        return LOGIN_COURIER;
    }

    public String getORDERS_URL() {
        return ORDERS_URL;
    }

    public String getACCEPT_ORDER() {
        return ACCEPT_ORDER;
    }

    public String getGET_ORDER() {
        return GET_ORDER;
    }
    public ValidatableResponse doPostRequest(String url, Object body) {
        RequestSpecification request = given(baseRequest());
        request.body(body);
        return request.post(url).then();
    }
    public ValidatableResponse doGetRequest(String url) {
        return given(baseRequest()).get(url).then();
    }
    public ValidatableResponse doGetRequestWithQuery(String url, String name, String value) {
        return given(baseRequest()).queryParam(name, value).get(url).then();
    }
    public ValidatableResponse doPutRequestWithQuery(String url, String name, String value) {
        return given(baseRequest()).queryParam(name, value).put(url).then();
    }
    public ValidatableResponse doDeleteRequest(String url) {
        return given(baseRequest()).delete(url).then();
    }
    public RequestSpecification baseRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setRelaxedHTTPSValidation()
                .build();
    }

}
