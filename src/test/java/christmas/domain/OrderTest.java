package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @DisplayName("주문한 메뉴의 총 가격을 정확히 계산하는지 테스트")
    @Test
    public void addItem_ShouldCorrectlyUpdateTotalPrice() {
        // given
        Order order = new Order();
        MenuItem item = MenuItem.MUSHROOM_SOUP;
        int quantity = 2;
        int expected = item.getPrice() * quantity;

        // when
        order.addItem(item, quantity);
        int actual = order.getTotalPrice();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
