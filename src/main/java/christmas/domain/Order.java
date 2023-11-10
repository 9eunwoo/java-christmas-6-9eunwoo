package christmas.domain;

import java.util.EnumMap;
import java.util.Map;

public class Order {
    private final Map<MenuItem, Integer> orderItems;

    public Order() {
        this.orderItems = new EnumMap<>(MenuItem.class);
    }

    public void addItem(MenuItem item, int quantity) {
        if (orderItems.containsKey(item)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        this.orderItems.put(item, quantity);
    }

    public int getTotalPrice() {
        return orderItems.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
