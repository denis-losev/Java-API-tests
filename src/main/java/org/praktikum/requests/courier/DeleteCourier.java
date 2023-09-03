package org.praktikum.requests.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;

public class DeleteCourier extends RequestUrls {
    Courier courier;

    public DeleteCourier(Courier courier) {
        this.courier = courier;
    }
    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(String id) {
        return doDeleteRequest(getCOURIER_URL() + "/" + id);
    }

}
