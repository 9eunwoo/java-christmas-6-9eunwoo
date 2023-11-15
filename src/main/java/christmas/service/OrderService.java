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
                .map(orderEntry -> orderEntry.split("-"))
                .collect(Collectors.toMap(
                        orderDetails -> MenuItem.fromString(orderDetails[0]),
                        orderDetails -> Order.parseQuantity(orderDetails[1]),
                        Order::throwDuplicateMenuException,
                        () -> new EnumMap<>(MenuItem.class)));
    }
}