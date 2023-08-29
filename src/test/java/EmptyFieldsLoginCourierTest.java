import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.LoginCourier;
import org.praktikum.requests.constants.RequestUrls;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsLoginCourierTest extends RequestUrls {
    private String login, password;

    public EmptyFieldsLoginCourierTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = getURL();
    }

    @Parameterized.Parameters
    public static Object[][] enterParameters() {
        return new Object[][]{
                {"", "P@s5w0rd"},
                {"Courier001", ""},
        };
    }

    @Test
    @DisplayName("Логин курьера: без передачи значения в поле")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")
    public void emptyFieldsLoginCourierTest() {
        Courier courier = new Courier(login, password);
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .then()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400)
                .log().status()
                .log().body();
    }
}
