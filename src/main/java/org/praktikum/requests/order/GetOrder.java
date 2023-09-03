package org.praktikum.requests.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.praktikum.requests.constants.RequestUrls;

public class GetOrder extends RequestUrls {
    Order order;

    public GetOrder(Order order) {
        this.order = order;
    }
    @Step("Получение заказа по ID")
    public ValidatableResponse getOrderById(String id) {
        return doGetRequestWithQuery(getGET_ORDER(), "t", id);
    }
}
