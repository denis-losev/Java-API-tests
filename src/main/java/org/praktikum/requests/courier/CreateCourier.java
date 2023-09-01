package org.praktikum.requests.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;
public class CreateCourier extends RequestUrls {
    Courier courier;
    public CreateCourier(Courier courier) {
        this.courier = courier;
    }
    @Step("Создание курьера")
    public ValidatableResponse createCourier() {
        return doPostRequest(getCOURIER_URL(), courier);
    }
}
