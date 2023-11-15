package christmas.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderServiceTest {
    @DisplayName("잘못된 주문이 들어왔을 때 예외를 잘 처리하는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "''", // 주문할 메뉴가 없다.
            "'해산물파스타-0,레드와인-3,제로콜라-4'", // 메뉴 수량은 1개 이상이여야 한다.
            "'해산물파스타-a,레드와인-3,제로콜라-4'", // 메뉴 수량은 숫자여야 한다.
            "'불닭볶음면-3'", // 메뉴에 없는 메뉴를 주문할 수 없다.
            "'레드와인-1,제로콜라-1'", // 음료만 주문할 수 없다.
            "'해산물파스타-10,레드와인-10,제로콜라-1'", // 주문 가능한 수량(20개)을 초과하였다.
            "'해산물파스타-21'" // 주문 가능한 수량(20개)을 초과하였다.
    })
    void createOrder(String orderForm) {
        // given
        OrderService orderService = new OrderService();

        // when & then
        assertThatThrownBy(() -> orderService.createValidOrder(orderForm))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
