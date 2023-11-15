package christmas.domain;

import java.util.Collections;
import java.util.Map;

public class Order {
    private static final int MAX_ORDER_QUANTITY = 20;
    private final Map<MenuItem, Integer> orderItems;

    private Order(Map<MenuItem, Integer> orderItems) {
        validate(orderItems);
        this.orderItems = orderItems;
    }

    public static Order create(Map<MenuItem, Integer> orderItems) {
        return new Order(orderItems);
    }

    public Map<MenuItem, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
    }

    public int calculateTotalPrice() {
        return orderItems.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().price() * entry.getValue())
                .sum();
    }

    public int calculateTotalQuantityByCategory(Category category) {
        return orderItems.entrySet()
                .stream()
                .filter(entry -> entry.getKey().category() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    private void validate(Map<MenuItem, Integer> orderItems) {
        if (orderItems.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 주문할 메뉴가 없습니다.");
        }
        if (isOrderOverLimit(orderItems)) {
            throw new IllegalArgumentException("[ERROR] 주문 가능한 수량(20개)을 초과하였습니다.]");
        }
        if (isBeverageOnly(orderItems)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private boolean isOrderOverLimit(Map<MenuItem, Integer> orderItems) {
        return orderItems.values().stream().mapToInt(Integer::intValue).sum() > MAX_ORDER_QUANTITY;
    }

    private boolean isBeverageOnly(Map<MenuItem, Integer> orderItems) {
        return orderItems.keySet().stream().allMatch(item -> item.category() == Category.BEVERAGE);
    }

    public static int parseQuantity(String number) {
        try {
            int quantity = Integer.parseInt(number);
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR] 주문 수량은 1개 이상이어야 합니다.");
            }
            if (quantity > MAX_ORDER_QUANTITY) {
                throw new IllegalArgumentException("[ERROR] 주문 가능한 수량(20개)을 초과하였습니다.]");
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 숫자여야 합니다.");
        }
    }

    public static <T> T throwDuplicateMenuException(T quantity1, T quantity2) {
        throw new IllegalArgumentException("[ERROR] 중복된 메뉴를 주문할 수 없습니다.");
    }
}
