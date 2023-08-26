package org.praktikum.requests;

public class Constants {
    private final String URL = "http://qa-scooter.praktikum-services.ru";
    private final String CREATE_COURIER = "/api/v1/courier";

    public String getCREATE_COURIER() {
        return URL + CREATE_COURIER;
    }


}
