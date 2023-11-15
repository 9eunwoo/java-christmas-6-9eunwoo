package christmas.service;

import christmas.domain.DiscountPolicy;
import christmas.domain.EventBadge;
import christmas.domain.GiftItem;
import christmas.domain.MenuItem;
import christmas.domain.Order;
import christmas.dto.EventDetailsDTO;
import java.util.Calendar;
import java.util.Map;

public class EventService {
    public EventDetailsDTO createEventDetails(Calendar calendar, Order order) {
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        int totalPrice = order.getTotalPrice();
        GiftItem giftItem = GiftItem.fromTotalPrice(totalPrice);
        int christmasDDayDiscount = DiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT.calculateDiscount(calendar, order);
        int weekdayDiscount = DiscountPolicy.WEEKDAY_DISCOUNT.calculateDiscount(calendar, order);
        int weekendDiscount = DiscountPolicy.WEEKEND_DISCOUNT.calculateDiscount(calendar, order);
        int specialDiscount = DiscountPolicy.SPECIAL_DISCOUNT.calculateDiscount(calendar, order);
        int totalDiscount = christmasDDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
        int totalBenefit = calcalateTotalBenefit(totalDiscount, giftItem);
        EventBadge eventBadge = EventBadge.fromTotalBenefit(totalBenefit);

        return new EventDetailsDTO(calendar, orderItems, totalPrice, giftItem,
                christmasDDayDiscount, weekdayDiscount, weekendDiscount, specialDiscount,
                totalDiscount, totalBenefit, eventBadge);
    }

    private int calcalateTotalBenefit(int totalDiscount, GiftItem giftItem) {
        return totalDiscount + (giftItem.get().price() * giftItem.quantity());
    }
}
