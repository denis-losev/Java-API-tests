import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsCreateCourierTest {
    private String login, password, firstName;

    public EmptyFieldsCreateCourierTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    @Parameterized.Parameters(name = "Login: {0}, Password: {1}, First name: {2}")
    public static Object[][] enterParameters() {
        return new Object[][]{
                {"", "P@s5w0rd", "Courier"},
                {"Courr1", "", "Couriers"},
                {"Couri07", "P@s5w0rd", ""}
        };
    }

    @Test
    @DisplayName("Создание курьера: без передачи значения в одно из полей")
    @Description("Если одного из полей нет, запрос возвращает ошибку")
    public void createCourierWithEmptyFieldsTest() {
        Courier courier = new Courier(login, password, firstName);
        CreateCourier createdCourier = new CreateCourier(courier);
        createdCourier.createCourier()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
