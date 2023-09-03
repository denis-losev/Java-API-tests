package org.praktikum.requests.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;

public class CreateOrder extends RequestUrls {
    Order order;
    public CreateOrder(Order order) {
        this.order = order;
    }
    @Step("Создание заказа")
    public ValidatableResponse createOrder() {
        return doPostRequest(getORDERS_URL(), order);
    }
    @Step("Получение ID заказа")
    public String getOrderId() {
        return createOrder().extract().body().as(OrderId.class).getTrack();
    }
}
