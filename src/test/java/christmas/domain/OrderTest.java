package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Map<MenuItem, Integer> orderItems;

    @BeforeEach
    void setUp() {
        orderItems = new EnumMap<>(MenuItem.class);
    }

    @DisplayName("주문한 메뉴의 총 가격을 정확히 계산하는지 테스트")
    @Test
    void addItem_ShouldCorrectlyUpdateTotalPrice() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 2;
        int expected = item.getPrice() * quantity;

        // when
        orderItems.put(item, quantity);
        Order order = Order.create(orderItems);
        int actual = order.getTotalPrice();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("주문한 메뉴가 없을 경우, 예외를 발생시키는지 테스트")
    @Test
    void finalizeOrder_ShouldThrowException_WhenNoItemOrdered() {
        // when & then
        assertThatThrownBy(() -> Order.create(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개를 초과할 경우, 예외를 발생시키는지 테스트")
    @Test
    void finalizeOrder_ShouldThrowException_WhenTotalQuantityIsOverTwenty() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 21;
        orderItems.put(item, quantity);

        // when & then
        assertThatThrownBy(() -> Order.create(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개 이하일 경우, 정상적으로 주문을 완료하는지 테스트")
    @Test
    void finalizeOrder_ShouldSuccessfullyFinalizeOrder_WhenTotalQuantityIsLessThanTwenty() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 20;
        orderItems.put(item, quantity);

        // when & then
        assertThatCode(() -> Order.create(orderItems)).doesNotThrowAnyException();
    }

    @DisplayName("음료만 주문한 경우, 예외를 발생시키는지 테스트")
    @Test
    void finalizeOrder_ShouldThrowException_WhenOnlyBeverageOrdered() {
        // given
        MenuItem item = MenuItem.ZERO_COLA;
        int quantity = 20;
        orderItems.put(item, quantity);

        // when & then
        assertThatThrownBy(() -> Order.create(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개 이하이고, 음료를 포함한 다른 메뉴를 주문한 경우, 정상적으로 주문을 완료하는지 테스트")
    @Test
    void finalizeOrder_ShouldSuccessfullyFinalizeOrder_WhenBeverageAndOtherItemsOrdered() {
        // given
        MenuItem item = MenuItem.ZERO_COLA;
        int quantity = 19;
        orderItems.put(item, quantity);
        item = MenuItem.MUSHROOM_SOUP;
        quantity = 1;
        orderItems.put(item, quantity);

        // when & then
        assertThatCode(() -> Order.create(orderItems)).doesNotThrowAnyException();
    }

    @DisplayName("특정 카테고리에 속하는 음식의 수량을 정확히 계산하는지 테스트")
    @Test
    void getTotalQuantityByCategory_ShouldCorrectlyCalculateTotalQuantityByCategory() {
        // given
        int totalAppetizerQuantity = 3;
        orderItems.put(MenuItem.MUSHROOM_SOUP, 2);
        orderItems.put(MenuItem.TAPAS, 1);
        orderItems.put(MenuItem.ZERO_COLA, 3);
        Order order = Order.create(orderItems);

        // when
        int actual = order.getTotalQuantityByCategory(Category.APPETIZER);

        // then
        assertThat(actual).isEqualTo(totalAppetizerQuantity);
    }
}
