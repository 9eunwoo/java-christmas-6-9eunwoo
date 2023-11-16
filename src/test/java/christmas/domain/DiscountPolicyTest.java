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
        int date = 25;
        calendar.set(2023, Calendar.DECEMBER, date); // 2023.12.25 (월) 크리스마스
        MenuItem item = MenuItem.CHOCO_CAKE; // 초코케이크는 디저트
        int quantity = 2;
        Map<MenuItem, Integer> orderItems = new EnumMap<>(MenuItem.class);
        orderItems.put(item, quantity);
        Order order = Order.create(orderItems);
        int expectedDiscount = 0;
        expectedDiscount += 1000 + 100 * (date - 1); // 크리스마스 디데이 할인(2023.12.1 ~ 25) 적용 o
        expectedDiscount += 2023 * 2; // 주중 할인(일~목, 디저트) o
        expectedDiscount += 1000; // 특별 할인(일, 크리스마스) 적용 o
        // when
        int actualDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            actualDiscount += discountPolicy.calculateDiscount(calendar, order);
        }
        // then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @DisplayName("총 구매 금액이 10,000원 미만일 때 할인이 적용되지 않는지 확인한다.")
    @Test
    void givenTotalPriceLessThanDiscountThreshold_whenCalculateDiscount_thenNoDiscount() {
        // given
        Calendar calendar = Calendar.getInstance();
        int date = 25;
        calendar.set(2023, Calendar.DECEMBER, date); // 2023.12.25 (월) 크리스마스
        MenuItem item = MenuItem.CAESAR_SALAD; // 시저샐러드 가격은 8,000원
        int quantity = 1;
        Map<MenuItem, Integer> orderItems = new EnumMap<>(MenuItem.class);
        orderItems.put(item, quantity);
        Order order = Order.create(orderItems);
        int expectedDiscount = 0;
        // when
        int actualDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            actualDiscount += discountPolicy.calculateDiscount(calendar, order);
        }
        // then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }

    @DisplayName("총 구매 금액이 10,000원 이상일 때 할인이 적용되는지 확인한다.")
    @Test
    void givenTotalPriceMoreThanDiscountThreshold_whenCalculateDiscount_thenDiscount() {
        // given
        Calendar calendar = Calendar.getInstance();
        int date = 25;
        calendar.set(2023, Calendar.DECEMBER, date); // 2023.12.25 (월) 크리스마스
        MenuItem item = MenuItem.ICE_CREAM; // 아이스크림는 디저트이고 가격은 5,000원
        int quantity = 2;
        Map<MenuItem, Integer> orderItems = new EnumMap<>(MenuItem.class);
        orderItems.put(item, quantity);
        Order order = Order.create(orderItems);
        int expectedDiscount = 0;
        expectedDiscount += 1000 + 100 * (date - 1); // 크리스마스 디데이 할인(2023.12.1 ~ 25) 적용 o
        expectedDiscount += 2023 * 2; // 주중 할인(일~목, 디저트) o
        expectedDiscount += 1000; // 특별 할인(일, 크리스마스) 적용 o
        // when
        int actualDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            actualDiscount += discountPolicy.calculateDiscount(calendar, order);
        }
        // then
        assertThat(actualDiscount).isEqualTo(expectedDiscount);
    }
}
