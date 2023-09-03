import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.courier.DeleteCourier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private String login = "Login999Co7rTest";
    private String password = "p@s5w0rd";
    private String firstName = "Courier";
    Courier createData = new Courier(login, password, firstName);
    Courier loginData = new Courier(login, password);
    CreateCourier createdCourier = new CreateCourier(createData);
    LoginCourier loginCourier = new LoginCourier(loginData);

    @Before
    public void setUp(){
        createdCourier.createCourier();
    }


    @Test
    @DisplayName("Логин курьера: успешный")
    @Description("Курьер может авторизоваться")
    public void loginCourierTest() {
        loginCourier.loginCourier()
                .assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @After
    public void clearCreatedData() {
        DeleteCourier deleteCourier = new DeleteCourier(loginData);
        deleteCourier.deleteCourier(loginCourier.getCouriersId());
    }
}
