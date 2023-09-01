package org.praktikum.requests.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;

public class LoginCourier extends RequestUrls {
    Courier courier;
    public LoginCourier(Courier courier) {
        this.courier = courier;
    }
    @Step("Логин курьера")
    public ValidatableResponse loginCourier() {
        return doPostRequest(getLOGIN_COURIER(), courier);
    }
    @Step("Получение ID курьера")
    public String getCouriersId() {
        return loginCourier().extract().body().as(CourierId.class).getId();
    }
}
