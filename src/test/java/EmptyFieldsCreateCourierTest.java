import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.constants.RequestUrls;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class EmptyFieldsCreateCourierTest extends RequestUrls {
    private String login, password, firstName;

    public EmptyFieldsCreateCourierTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    @Before
    public void setUp(){
        RestAssured.baseURI = getURL();
    }
    @Parameterized.Parameters
    public static Object[][] enterParameters() {
        return new Object[][]{
                {"", "P@s5w0rd", "Courier"},
                {"Courie0r1", "", "Couriers"},
                {"Couri5e8r07", "P@s5w0rd", ""}
        };
    }

    @Test
    @DisplayName("Создание курьера: без передачи значения в одно из полей")
    @Description("Если одного из полей нет, запрос возвращает ошибку")
    public void createCourierWithEmptyFieldsTest() {
        Courier courier = new Courier(login, password, firstName);
        CreateCourier createdCourier = new CreateCourier(courier);
        createdCourier.createCourier()
                .then()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400)
                .log().status()
                .log().body();
    }
}
