import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.courier.DeleteCourier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {
    private String login = "CouriLogin3";
    private String password = "p@s5w0rd";
    private String firstName = "Courier";

    Courier courier = new Courier(login, password, firstName);
    Courier dataForClearing = new Courier(login, password);
    CreateCourier createdCourier = new CreateCourier(courier);

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьера можно создать")
    public void createNewCourierTest() {
        createdCourier.createCourier()
                .assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера: дубликат")
    @Description("Нельзя создать двух одинаковых курьеров")
    public void createIdenticalCouriersTest() {
        createdCourier.createCourier();
        createdCourier.createCourier()
                .assertThat().statusCode(409)
                .and()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void clearCreatedData() {
        DeleteCourier deleteCourier = new DeleteCourier(dataForClearing);
        LoginCourier loginCourier = new LoginCourier(dataForClearing);
        deleteCourier.deleteCourier(loginCourier.getCouriersId());
    }
}
