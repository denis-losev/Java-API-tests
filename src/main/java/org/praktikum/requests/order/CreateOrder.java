package org.praktikum.requests.order;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;

public class CreateOrder extends RequestUrls {
    Order order;
    public CreateOrder(Order order) {
        this.order = order;
    }
    public Response createOrder() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(getCREATE_ORDER());
    }

    public String getOrderId() {
        return createOrder().body().as(OrderId.class).getTrack();
    }
}
