import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.courier.DeleteCourier;
import org.praktikum.requests.courier.LoginCourier;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierTest {
    private String login = "Courier2Login5";
    private String password = "p@s5w0rd";
    private String firstName = "Courier";

    Courier courier = new Courier(login, password, firstName);
    CreateCourier createdCourier = new CreateCourier(courier);
    Courier delCourier = new Courier(login, password);
    DeleteCourier deleteCourier = new DeleteCourier(delCourier);
    LoginCourier loginCourier = new LoginCourier(delCourier);

    @Before
    public void setUp(){
        createdCourier.createCourier();
    }

    @Test
    @DisplayName("Удалить курьера")
    @Description("Успешный запрос возвращает ok: true")
    public void deleteCourierTest() {
        deleteCourier.deleteCourier(loginCourier.getCouriersId())
                .assertThat().statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Удалить курьера: без указания id")
    @Description("Если запрос без id, то вернётся ошибка")
    public void deleteCourierWithoutIdTest() {
        deleteCourier.deleteCourier("")
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для удаления курьера"));
    }

    @Test
    @DisplayName("Удалить курьера: указать несуществующий id")
    @Description("Если отправить запрос с несуществующим id, вернётся ошибка")
    public void deleteCourierNonExistentIdTest() {
        deleteCourier.deleteCourier("99999999")
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Курьера с таким id нет"));
    }
}
