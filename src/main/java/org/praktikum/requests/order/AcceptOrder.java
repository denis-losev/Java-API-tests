package org.praktikum.requests.order;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;

import static io.restassured.RestAssured.given;

public class AcceptOrder extends RequestUrls {
    CreateOrder order;
    CreateCourier courier;

    public AcceptOrder(CreateOrder order, CreateCourier courier) {
        this.order = order;
        this.courier = courier;
    }

    public Response acceptOrder(String track, String id) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", id)
                .put(getACCEPT_ORDER() + track);
    }
}
