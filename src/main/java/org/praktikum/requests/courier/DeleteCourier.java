package org.praktikum.requests.courier;

import io.restassured.response.Response;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;

public class DeleteCourier extends RequestUrls {
    Courier courier;

    public DeleteCourier(Courier courier) {
        this.courier = courier;
    }
    public Response deleteCourier(String id) {
        return given()
                .header("Content-type", "application/json")
                .delete(getDELETE_COURIER() + id);
    }

}
