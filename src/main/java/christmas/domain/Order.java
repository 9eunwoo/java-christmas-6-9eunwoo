package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class Order {
     private final Map<MenuItem, Integer> orderItems;

    public Order() {
        this.orderItems = new EnumMap<>(MenuItem.class);
    }

    public void addItem(MenuItem item, int quantity) {
        this.orderItems.put(item, quantity);
    }

    public int getTotalPrice() {
        return orderItems.entrySet()
                         .stream()
                         .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                         .sum();
    }
}
