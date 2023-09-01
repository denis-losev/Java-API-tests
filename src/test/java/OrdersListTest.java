import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.praktikum.requests.constants.RequestUrls;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest extends RequestUrls {
    @Test
    @DisplayName("Список заказов")
    @Description("В тело ответа возвращается список заказов")
    public void orderListTest() {
        doGetRequest(getORDERS_URL())
                .assertThat().statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}
