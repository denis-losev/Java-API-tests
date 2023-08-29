import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.LoginCourier;
import org.praktikum.requests.constants.RequestUrls;

import static org.hamcrest.CoreMatchers.equalTo;

public class NonExistentAccountLoginTest extends RequestUrls {
    private String login = "Non-ExistentLogin";
    private String password = "Password";
    Courier courier = new Courier(login, password);

    @Before
    public void setUp(){
        RestAssured.baseURI = getURL();
    }

    @Test
    @DisplayName("Логин курьера: несуществующий пользователь")
    @Description("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void nonExistentAccountLoginTest() {
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404)
                .log().status()
                .log().body();
    }
}
