import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.praktikum.requests.order.CreateOrder;
import org.praktikum.requests.order.Order;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private String firstName, lastName, address, metroStation, phone, deliveryDate, comment;
    private int rentTime;
    private String[] color;
    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.rentTime = rentTime;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Имя: {0}, Фамилия: {1}, Адресс: {2}, Название станции метро: {3}, Телефон: {4}, Время аренды: {5}, Дата доставки: {6}, Комментарий: {7}, Цвет: {8}")
    public static Object[][] enterParameters() {
        return new Object[][] {
                {"Денис", "Лосев", "Ленина 10", "2", "+79777777777", 1, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{"BLACK", "GREY"}},
                {"Денис", "Лосев", "Ленина 10", "3", "+79777777777", 2, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{"BLACK"}},
                {"Денис", "Лосев", "Ленина 10", "4", "+79777777777", 3, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{"GREY"}},
                {"Денис", "Лосев", "Ленина 10", "5", "+79777777777", 4, "2023-08-30T21:00:00.000Z", "Комментарий", new String[]{}}
        };
    }

    @Test
    @DisplayName("Параметризованная проверка создания заказа")
    @Description("можно указать один из цветов — BLACK или GREY;\n" +
            "можно указать оба цвета;\n" +
            "можно совсем не указывать цвет;\n" +
            "тело ответа содержит track.")
    public void createOrderTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        CreateOrder createOrder = new CreateOrder(order);
        createOrder.createOrder()
                .assertThat().statusCode(201)
                .and()
                .body("track", notNullValue());
    }
}
