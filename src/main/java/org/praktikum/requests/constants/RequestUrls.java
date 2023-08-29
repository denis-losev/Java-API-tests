package org.praktikum.requests.constants;

public class RequestUrls {
    private final String URL = "http://qa-scooter.praktikum-services.ru";
    private final String CREATE_COURIER = "/api/v1/courier";
    private final String DELETE_COURIER = "/api/v1/courier/";
    private final String LOGIN_COURIER = "/api/v1/courier/login";
    private final String CREATE_ORDER = "/api/v1/orders";
    private final String ORDERS_LIST = "/api/v1/orders";
    private final String ACCEPT_ORDER = "/api/v1/orders/accept/";
    private final String GET_ORDER = "/api/v1/orders/track";

    public String getURL() {
        return URL;
    }
    public String getCREATE_COURIER() {
        return CREATE_COURIER;
    }

    public String getDELETE_COURIER() {
        return DELETE_COURIER;
    }

    public String getLOGIN_COURIER() {
        return LOGIN_COURIER;
    }

    public String getCREATE_ORDER() {
        return CREATE_ORDER;
    }

    public String getORDERS_LIST() {
        return ORDERS_LIST;
    }

    public String getACCEPT_ORDER() {
        return ACCEPT_ORDER;
    }

    public String getGET_ORDER() {
        return GET_ORDER;
    }
}
