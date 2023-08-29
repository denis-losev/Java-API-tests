package org.praktikum.requests.order;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;

public class GetOrder extends RequestUrls {
    Order order;

    public GetOrder(Order order) {
        this.order = order;
    }

    public Response getOrderById(String id) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("t", id)
                .get(getGET_ORDER());
    }
}
