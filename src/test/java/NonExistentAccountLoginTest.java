import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

public class NonExistentAccountLoginTest {
    private String login = "Non-ExistentLogin";
    private String password = "Password";
    Courier courier = new Courier(login, password);

    @Test
    @DisplayName("Логин курьера: несуществующий пользователь")
    @Description("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void nonExistentAccountLoginTest() {
        LoginCourier loginCourier = new LoginCourier(courier);
        loginCourier.loginCourier()
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
