package christmas.domain;

import christmas.constant.Message;
import java.util.EnumMap;
import java.util.Map;

public class Order {
    private static final int MAX_ORDER_QUANTITY = 20;
    private final Map<MenuItem, Integer> orderItems;

    public Order() {
        this.orderItems = new EnumMap<>(MenuItem.class);
    }

    public void addItem(MenuItem item, int quantity) {
        if (orderItems.containsKey(item) || quantity <= 0) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_ORDER.getMessage());
        }
        this.orderItems.put(item, quantity);
    }

    public int getTotalPrice() {
        return orderItems.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int getTotalQuantityByCategory(Category category) {
        return orderItems.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public void finalizeOrder() {
        if (orderItems.isEmpty() || isOrderOverLimit() || isBeverageOnly()) {
            throw new IllegalStateException(Message.ERROR_INVALID_ORDER.getMessage());
        }
    }

    private boolean isOrderOverLimit() {
        return orderItems.values().stream().mapToInt(Integer::intValue).sum() > MAX_ORDER_QUANTITY;
    }

    private boolean isBeverageOnly() {
        return orderItems.keySet().stream().allMatch(item -> item.getCategory() == Category.BEVERAGE);
    }
}
