package christmas.dto;

import christmas.domain.EventBadge;
import christmas.domain.GiftItem;
import christmas.domain.MenuItem;
import java.util.Calendar;
import java.util.Map;

public record EventDetailsDTO(
        Calendar calendar, Map<MenuItem, Integer> orderItems, int totalPrice, GiftItem giftItem,
        int christmasDDayDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
        int totalDiscount, int totalBenefit, EventBadge eventBadge) {

    public static EventDetailsDTO createWithNoBenefit(
            Calendar calendar, Map<MenuItem, Integer> orderItems, int totalPrice) {
        return new EventDetailsDTO(calendar, orderItems, totalPrice, GiftItem.NONE,
                0, 0, 0, 0,
                0, 0, EventBadge.NONE);
    }

    public static EventDetailsDTO createWithBenefit(
            Calendar calendar, Map<MenuItem, Integer> orderItems, int totalPrice, GiftItem giftItem,
            int christmasDDayDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount,
            int totalDiscount, int totalBenefit, EventBadge eventBadge) {
        return new EventDetailsDTO(calendar, orderItems, totalPrice, giftItem,
                christmasDDayDiscount, weekdayDiscount, weekendDiscount, specialDiscount,
                totalDiscount, totalBenefit, eventBadge);
    }
}