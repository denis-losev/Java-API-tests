package org.praktikum.requests.courier;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;

public class LoginCourier extends RequestUrls {
    Courier courier;
    public LoginCourier(Courier courier) {
        this.courier = courier;
    }

    public Response loginCourier() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(getLOGIN_COURIER());
    }

    public String getCouriersId() {
        return loginCourier().body().as(CourierId.class).getId();
    }
}
