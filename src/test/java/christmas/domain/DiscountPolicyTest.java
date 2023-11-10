package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountPolicyTest {
    @DisplayName("할인 정책에 따라 할인 금액을 정확히 계산하는지 테스트")
    @Test
    void givenDiscountPolicy_whenCalculateDiscount_thenCorrectDiscountCalculated() {
        // given
        Order order = new Order();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, 25); // 2023.12.25 (월) 크리스마스
        MenuItem item = MenuItem.CHOCO_CAKE; // 초코케이크는 디저트
        int quantity = 2;
        order.addItem(item, quantity);
        order.finalizeOrder();
        int expectedDiscount = 1000 + 100 * (25 - 1); // 2023.12.1 ~ 25는 크리스마스 디데이 할인 적용 o
        expectedDiscount += 2023 * quantity; // 주중 할인(일~목, 디저트) o
        expectedDiscount += 0; // 주말 할인(금~토, 메인) x
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
