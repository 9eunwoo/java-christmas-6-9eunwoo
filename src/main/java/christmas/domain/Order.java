package christmas.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Order {
    private static final int MAX_ORDER_QUANTITY = 20;
    private final Map<MenuItem, Integer> orderItems;
    private boolean isFinalized;

    public Order() {
        this.orderItems = new EnumMap<>(MenuItem.class);
        this.isFinalized = false;
    }

    public void addItem(MenuItem item, int quantity) {
        if (isFinalized) {
            throw new IllegalStateException("[ERROR] 이미 주문이 완료되었습니다. 추가 주문은 불가능합니다.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 1개 이상이어야 합니다.");
        }
        if (orderItems.containsKey(item)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴를 주문할 수 없습니다.");
        }
        this.orderItems.put(item, quantity);
    }

    public Map<MenuItem, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
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
        if (orderItems.isEmpty()) {
            throw new IllegalStateException("[ERROR] 주문할 메뉴가 없습니다.");
        }
        if (isOrderOverLimit()) {
            throw new IllegalStateException("[ERROR] 주문 가능한 수량(20개)을 초과하였습니다.]");
        }
        if (isBeverageOnly()) {
            throw new IllegalStateException("[ERROR] 음료만 주문할 수 없습니다.");
        }
        isFinalized = true;
    }

    private boolean isOrderOverLimit() {
        return orderItems.values().stream().mapToInt(Integer::intValue).sum() > MAX_ORDER_QUANTITY;
    }

    private boolean isBeverageOnly() {
        return orderItems.keySet().stream().allMatch(item -> item.getCategory() == Category.BEVERAGE);
    }
}
