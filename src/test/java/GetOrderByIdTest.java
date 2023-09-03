import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.order.CreateOrder;
import org.praktikum.requests.order.GetOrder;
import org.praktikum.requests.order.Order;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderByIdTest {
    Order order = new Order("Денис", "Лосев", "Ленина 10", "4", "+79777777777", 3, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{"GREY"});
    CreateOrder createdOrder = new CreateOrder(order);
    GetOrder getOrder = new GetOrder(order);

    @Before
    public void setUp() {
        createdOrder.createOrder();
    }

    @Test
    @DisplayName("Получить заказ по его номеру: успешный запрос")
    @Description("Успешный запрос возвращает объект с заказом")
    public void getOrderByIdTest() {
        getOrder.getOrderById(createdOrder.getOrderId())
                .assertThat().body("order", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Получить заказ по его номеру: без указания номера")
    @Description("Запрос без номера заказа возвращает ошибку")
    public void getOrderByEmptyIdTest() {
        getOrder.getOrderById("")
                .assertThat().body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Получить заказ по его номеру: указать номер несуществующего заказа")
    @Description("Запрос с несуществующим заказом возвращает ошибку")
    public void getOrderByNonExistentIdTest() {
        getOrder.getOrderById("9786598")
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Заказ не найден"));
    }
}
