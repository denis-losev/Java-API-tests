import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.praktikum.requests.courier.Courier;
import org.praktikum.requests.courier.CreateCourier;
import org.praktikum.requests.courier.DeleteCourier;
import org.praktikum.requests.courier.LoginCourier;
import org.praktikum.requests.order.AcceptOrder;
import org.praktikum.requests.order.CreateOrder;
import org.praktikum.requests.order.Order;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderTest {
    private String login = "LoginCourier1999";
    private String password = "p@s5w0rd";
    private String firstName = "Courier";
    Order order = new Order("Денис", "Лосев", "Ленина 10", "4", "+79777777777", 3, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{"GREY"});
    Courier courier = new Courier(login, password, firstName);
    Courier loginData = new Courier(login, password);
    CreateCourier createdCourier = new CreateCourier(courier);
    CreateOrder createdOrder = new CreateOrder(order);
    LoginCourier loginCourier = new LoginCourier(loginData);
    AcceptOrder acceptOrder = new AcceptOrder(createdOrder, createdCourier);
    @Before
    public void setUp(){
        createdCourier.createCourier();
        createdOrder.createOrder();
    }

    @Test
    @DisplayName("Успешное принятие заказа")
    @Description("Успешный запрос возвращает ok: true")
    public void acceptOrderTest() {
        acceptOrder.acceptOrder(createdOrder.getOrderId(), loginCourier.getCouriersId())
                .assertThat().statusCode(200)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Принять заказ: без указания id курьера")
    @Description("Если не передать id курьера, запрос вернёт ошибку")
    public void acceptOrderNoCourierIdTest() {
        acceptOrder.acceptOrder(createdOrder.getOrderId(), "")
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Принять заказ: указать неверный id курьера")
    @Description("Если передать неверный id курьера, запрос вернёт ошибку")
    public void acceptOrderWithNonExistentCourierIdTest() {
        acceptOrder.acceptOrder(createdOrder.getOrderId(), "869098543")
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Курьера с таким id не существует"));
    }

    @Test
    @DisplayName("Принять заказ: без указания номера заказа")
    @Description("Если не передать номер заказа, запрос вернёт ошибку")
    public void acceptOrderNoOrderIdTest() {
        acceptOrder.acceptOrder("", loginCourier.getCouriersId())
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Принять заказ: указать неверный номер заказа")
    @Description("Если не передать неверный номер заказа, запрос вернёт ошибку")
    public void acceptOrderWithNonExistentOrderIdTest() {
        acceptOrder.acceptOrder("199189218", loginCourier.getCouriersId())
                .assertThat().statusCode(404)
                .and()
                .body("message", equalTo("Заказа с таким id не существует"));
    }

    @After
    public void clearCreatedData() {
        DeleteCourier deleteCourier = new DeleteCourier(loginData);
        deleteCourier.deleteCourier(loginCourier.getCouriersId());
    }
}
