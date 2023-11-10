package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.Constant.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @DisplayName("주문한 메뉴의 총 가격을 정확히 계산하는지 테스트")
    @Test
    void addItem_ShouldCorrectlyUpdateTotalPrice() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 2;
        int expected = item.getPrice() * quantity;

        // when
        order.addItem(item, quantity);
        int actual = order.getTotalPrice();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("중복 메뉴를 주문할 경우, 예외를 발생시키는지 테스트")
    @Test
    void addItem_ShouldThrowException_WhenDuplicateItemAdded() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 2;
        order.addItem(item, quantity);

        // when & then
        assertThatThrownBy(() -> order.addItem(item, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Message.ERROR_INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴의 수량이 0개 이하일 경우, 예외를 발생시키는지 테스트")
    @Test
    void addItem_ShouldThrowException_WhenQuantityIsZeroOrNegative() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 0;

        // when & then
        assertThatThrownBy(() -> order.addItem(item, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Message.ERROR_INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개를 초과할 경우, 예외를 발생시키는지 테스트")
    @Test
    void finalizeOrder_ShouldThrowException_WhenTotalQuantityIsOverTwenty() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 21;
        order.addItem(item, quantity);

        // when & then
        assertThatThrownBy(() -> order.finalizeOrder())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Message.ERROR_INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개 이하일 경우, 정상적으로 주문을 완료하는지 테스트")
    @Test
    void finalizeOrder_ShouldSuccessfullyFinalizeOrder_WhenTotalQuantityIsLessThanTwenty() {
        // given
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 20;
        order.addItem(item, quantity);

        // when & then
        assertThatCode(() -> order.finalizeOrder()).doesNotThrowAnyException();
    }

    @DisplayName("음료만 주문한 경우, 예외를 발생시키는지 테스트")
    @Test
    void finalizeOrder_ShouldThrowException_WhenOnlyBeverageOrdered() {
        // given
        MenuItem item = MenuItem.ZERO_COLA;
        int quantity = 20;
        order.addItem(item, quantity);

        // when & then
        assertThatThrownBy(() -> order.finalizeOrder())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Message.ERROR_INVALID_ORDER.getMessage());
    }

    @DisplayName("주문한 메뉴의 총 수량이 20개 이하이고, 음료를 포함한 다른 메뉴를 주문한 경우, 정상적으로 주문을 완료하는지 테스트")
    @Test
    void finalizeOrder_ShouldSuccessfullyFinalizeOrder_WhenBeverageAndOtherItemsOrdered() {
        // given
        MenuItem item = MenuItem.ZERO_COLA;
        int quantity = 19;
        order.addItem(item, quantity);
        item = MenuItem.MUSHROOM_SOUP;
        quantity = 1;
        order.addItem(item, quantity);

        // when & then
        assertThatCode(() -> order.finalizeOrder()).doesNotThrowAnyException();
    }

    @DisplayName("특정 카테고리에 속하는 음식의 수량을 정확히 계산하는지 테스트")
    @Test
    void getTotalQuantityByCategory_ShouldCorrectlyCalculateTotalQuantityByCategory() {
        // given
        MenuItem item;;
        int totalAppetizerQuantity = 3;
        order.addItem(MenuItem.MUSHROOM_SOUP, 2);
        order.addItem(MenuItem.TAPAS, 1);
        order.addItem(MenuItem.ZERO_COLA, 3);

        // when
        int actual = order.getTotalQuantityByCategory(Category.APPETIZER);

        // then
        assertThat(actual).isEqualTo(totalAppetizerQuantity);
    }
}
