package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountPolicyTest {
    @DisplayName("할인 정책에 따라 할인 금액을 정확히 계산하는지 테스트")
    @Test
    void givenDiscountPolicy_whenCalculateDiscount_thenCorrectDiscountCalculated() {
        // given
        Order order = new Order();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, 25);
        MenuItem item = MenuItem.TBONE_STEAK;
        int quantity = 2;
        order.addItem(item, quantity);
        order.finalizeOrder();
        int expectedDiscount = 1000 + 100 * (25 - 1); // 크리스마스 디데이 할인 적용 o
        expectedDiscount += 2023 * quantity; // 2023년 12월 25일은 월요일이므로 주중 할인 적용 o
        expectedDiscount += 0; // 2023년 12월 25일은 월요일이므로 주말 할인 적용 x
        expectedDiscount += 1000; // 크리스마스 당일은 특별 할인 적용 o
        // when
        int actualDiscount = DiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT.calculateDiscount(order, calendar);
        actualDiscount += DiscountPolicy.WEEKDAY_DISCOUNT.calculateDiscount(order, calendar);
        actualDiscount += DiscountPolicy.WEEKEND_DISCOUNT.calculateDiscount(order, calendar);
        actualDiscount += DiscountPolicy.SPECIAL_DISCOUNT.calculateDiscount(order, calendar);
        // then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}
