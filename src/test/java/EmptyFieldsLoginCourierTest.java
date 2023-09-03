import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsLoginCourierTest {
    private String login, password;

    public EmptyFieldsLoginCourierTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Login: {0}, Password: {1}")
    public static Object[][] enterParameters() {
        return new Object[][]{
                {"", "P@s5w0rd"},
                {"Courjhkb001", ""},
        };
    }

    @Test
    @DisplayName("Логин курьера: без передачи значения в поле")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")
    public void emptyFieldsLoginCourierTest() {
        Courier courier = new Courier(login, password);
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
