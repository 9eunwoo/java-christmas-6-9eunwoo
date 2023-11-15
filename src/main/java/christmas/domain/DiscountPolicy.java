package christmas.domain;

import java.util.Calendar;

public enum DiscountPolicy {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인") {
        @Override
        protected int calculateDiscountInternal(Calendar calendar, Order order) {
            int date = calendar.get(Calendar.DATE);
            if (date <= CHRISTMAS_DAY) {
                return CHRISTMAS_D_DAY_DISCOUNT_BASE + CHRISTMAS_D_DAY_DISCOUNT_MULTIPLIER * (date - 1);
            }
            return 0;
        }
    },
    WEEKDAY_DISCOUNT("평일 할인") {
        @Override
        protected int calculateDiscountInternal(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
                return WEEKDAY_DISCOUNT_MULTIPLIER * order.calculateTotalQuantityByCategory(Category.DESSERT);
            }
            return 0;
        }
    },
    WEEKEND_DISCOUNT("주말 할인") {
        @Override
        protected int calculateDiscountInternal(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.FRIDAY || dayOfWeek == Calendar.SATURDAY) {
                return WEEKEND_DISCOUNT_MULTIPLIER * order.calculateTotalQuantityByCategory(Category.MAIN);
            }
            return 0;
        }
    },
    SPECIAL_DISCOUNT("특별 할인") {
        @Override
        protected int calculateDiscountInternal(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int date = calendar.get(Calendar.DATE);
            if (dayOfWeek == Calendar.SUNDAY || date == CHRISTMAS_DAY) {
                return SPECIAL_DAY_DISCOUNT_AMOUNT;
            }
            return 0;
        }
    };

    private static final int DISCOUNT_THRESHOLD = 10_000;
    private static final int CHRISTMAS_DAY = 25;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_BASE = 1000;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_MULTIPLIER = 100;
    private static final int WEEKDAY_DISCOUNT_MULTIPLIER = 2023;
    private static final int WEEKEND_DISCOUNT_MULTIPLIER = 2023;
    private static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    private final String name;

    DiscountPolicy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private static boolean isEligibleForDiscount(Order order) {
        return order.getTotalPrice() >= DISCOUNT_THRESHOLD;
    }

    public int calculateDiscount(Calendar calendar, Order order) {
        if (!isEligibleForDiscount(order)) {
            return 0;
        }
        return calculateDiscountInternal(calendar, order);
    }

    protected abstract int calculateDiscountInternal(Calendar calendar, Order order);
}