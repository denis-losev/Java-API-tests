package org.praktikum.requests.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;
import org.praktikum.requests.courier.CreateCourier;

public class AcceptOrder extends RequestUrls {
    CreateOrder order;
    CreateCourier courier;

    public AcceptOrder(CreateOrder order, CreateCourier courier) {
        this.order = order;
        this.courier = courier;
    }
    @Step("Принятие заказа")
    public ValidatableResponse acceptOrder(String track, String id) {
        return doPutRequestWithQuery(getACCEPT_ORDER(), "courierId", id);
    }
}
