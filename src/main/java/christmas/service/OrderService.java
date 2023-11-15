package christmas.service;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderService {
    public Order createValidOrder(String orderForm) {
        Map<MenuItem, Integer> orderItems = parseOrderForm(orderForm);
        return Order.create(orderItems);
    }

    private Map<MenuItem, Integer> parseOrderForm(String orderForm) {
        return Arrays.stream(orderForm.split(","))
                .map(menuEntry -> menuEntry.split("-"))
                .collect(Collectors.toMap(
                        menuDetails -> MenuItem.fromString(menuDetails[0]),
                        menuDetails -> Integer.parseInt(menuDetails[1]),
                        this::throwDuplicateMenuException,
                        () -> new EnumMap<>(MenuItem.class)));
    }

    private <T> T throwDuplicateMenuException(T quantity1, T quantity2) {
        throw new IllegalArgumentException("[ERROR] 중복된 메뉴를 주문할 수 없습니다.");
    }
}