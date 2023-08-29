import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.constants.RequestUrls;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest extends RequestUrls {
    @Before
    public void setUp(){
        RestAssured.baseURI = getURL();
    }

    @Test
    @DisplayName("Список заказов")
    @Description("В тело ответа возвращается список заказов")
    public void orderListTest() {
        given()
                .get(getORDERS_LIST())
                .then()
                .assertThat().body("orders", notNullValue())
                .log().status()
                .log().body();
    }
}
