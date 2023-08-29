package org.praktikum.requests.courier;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;
public class CreateCourier extends RequestUrls {
    Courier courier;
    public CreateCourier(Courier courier) {
        this.courier = courier;
    }

    public Response createCourier() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(getCREATE_COURIER());
    }
}
