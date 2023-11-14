package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountPolicyTest {
    @DisplayName("할인 정책에 따라 할인 금액이 정확히 계산되는지 확인한다.")
    @Test
    void givenDiscountPolicy_whenCalculateDiscount_thenCorrectDiscountCalculated() {
        // given
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = 25;
        calendar.set(2023, Calendar.DECEMBER, dayOfMonth); // 2023.12.25 (월) 크리스마스
        MenuItem item = MenuItem.CHOCO_CAKE; // 초코케이크는 디저트
        int quantity = 2;
        Map<MenuItem, Integer> orderItems = new EnumMap<>(MenuItem.class);
        orderItems.put(item, quantity);
        Order order = Order.create(orderItems);
        int expectedDiscount = 0;
        expectedDiscount += 1000 + 100 * (dayOfMonth - 1); // 크리스마스 디데이 할인(2023.12.1 ~ 25) 적용 o
        expectedDiscount += 2023 * order.getTotalQuantityByCategory(Category.DESSERT); // 주중 할인(일~목, 디저트) o
        expectedDiscount += 2023 * order.getTotalQuantityByCategory(Category.MAIN); // 주말 할인(금~토, 메인) x
        expectedDiscount += 1000; // 특별 할인(일, 크리스마스) 적용 o
        // when
        int actualDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            actualDiscount += discountPolicy.calculateDiscount(order, calendar);
        }
        // then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}
