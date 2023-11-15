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
}