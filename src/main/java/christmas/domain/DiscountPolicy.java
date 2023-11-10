package christmas.domain;

import java.util.Calendar;

public enum DiscountPolicy {
    CHRISTMAS_D_DAY_DISCOUNT {
        @Override
        public int calculateDiscount(Order order, Calendar calendar) {
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth <= 25) {
                return 1000 + 100 * (dayOfMonth - 1);
            }
            return 0;
        }
    },
    WEEKDAY_DISCOUNT {
        @Override
        public int calculateDiscount(Order order, Calendar calendar) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek >= Calendar.SUNDAY && dayOfWeek <= Calendar.THURSDAY) {
                return 2023 * order.getTotalQuantityByCategory(Category.DESSERT);
            }
            return 0;
        }
    },
    WEEKEND_DISCOUNT {
        @Override
        public int calculateDiscount(Order order, Calendar calendar) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek >= Calendar.FRIDAY && dayOfWeek <= Calendar.SATURDAY) {
                return 2023 * order.getTotalQuantityByCategory(Category.MAIN);
            }
            return 0;
        }
    },
    SPECIAL_DISCOUNT {
        @Override
        public int calculateDiscount(Order order, Calendar calendar) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            if (dayOfWeek == Calendar.SUNDAY || dayOfMonth == 25) {
                return 1000;
            }
            return 0;
        }
    };

    public abstract int calculateDiscount(Order order, Calendar calendar);
}