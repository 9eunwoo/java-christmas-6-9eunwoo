package christmas.domain;

import java.util.Calendar;

public enum DiscountPolicy {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인") {
        @Override
        public int calculateDiscount(Calendar calendar, Order order) {
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth <= 25) {
                return 1000 + 100 * (dayOfMonth - 1);
            }
            return 0;
        }
    },
    WEEKDAY_DISCOUNT("평일 할인") {
        @Override
        public int calculateDiscount(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
                return 2023 * order.calculateTotalQuantityByCategory(Category.DESSERT);
            }
            return 0;
        }
    },
    WEEKEND_DISCOUNT("주말 할인") {
        @Override
        public int calculateDiscount(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek >= Calendar.FRIDAY && dayOfWeek <= Calendar.SATURDAY) {
                return 2023 * order.calculateTotalQuantityByCategory(Category.MAIN);
            }
            return 0;
        }
    },
    SPECIAL_DISCOUNT("특별 할인") {
        @Override
        public int calculateDiscount(Calendar calendar, Order order) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == 25) {
                return 1000;
            }
            return 0;
        }
    };

    private final String name;

    DiscountPolicy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract int calculateDiscount(Calendar calendar, Order order);
}