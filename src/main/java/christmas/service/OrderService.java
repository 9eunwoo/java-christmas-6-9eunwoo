package christmas.service;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class OrderService {
    public Order createOrder(Map<MenuItem, Integer> orderItems) {
        Order order = new Order();
        orderItems.forEach(order::addItem);
        order.finalizeOrder();
        return order;
    }

    public Map<MenuItem, Integer> parseOrderInput(String orderInput) {
        Map<MenuItem, Integer> orderItems = new EnumMap<>(MenuItem.class);
        Arrays.stream(orderInput.split(","))
                .forEach(menuEntry -> {
                    String[] menuDetails = menuEntry.split("-");
                    String menuName = menuDetails[0];
                    int quantity = Integer.parseInt(menuDetails[1]);
                    MenuItem menuItem = MenuItem.getByName(menuName);
                    orderItems.put(menuItem, quantity);
                });
        return orderItems;
    }
}