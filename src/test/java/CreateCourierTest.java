import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.constants.RequestUrls;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.courier.DeleteCourier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest extends RequestUrls {
    private String login = "Courier3333Login3";
    private String password = "p@s5w0rd";
    private String firstName = "Courier";

    Courier courier = new Courier(login, password, firstName);
    Courier dataForClearing = new Courier(login, password);
    CreateCourier createdCourier = new CreateCourier(courier);
    @Before
    public void setUp(){
        RestAssured.baseURI = getURL();
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьера можно создать")
    public void createNewCourierTest() {
        createdCourier.createCourier()
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201)
                .log().body();
    }

    @Test
    @DisplayName("Создание курьера: дубликат")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void createIdenticalCouriersTest() {
        createdCourier.createCourier();
        createdCourier.createCourier()
                .then()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409)
                .log().status()
                .log().body();
    }

    @After
    public void clearCreatedData() {
        DeleteCourier deleteCourier = new DeleteCourier(dataForClearing);
        LoginCourier loginCourier = new LoginCourier(dataForClearing);
        deleteCourier.deleteCourier(loginCourier.getCouriersId());
    }
}
