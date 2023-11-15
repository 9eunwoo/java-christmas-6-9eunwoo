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
    private static final int EVENT_THRESHOLD = 10_000;

    public EventDetailsDTO createEventDetails(Calendar calendar, Order order) {
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        int totalPrice = order.calculateTotalPrice();
        if (totalPrice < EVENT_THRESHOLD) {
            return EventDetailsDTO.createWithNoBenefit(calendar, orderItems, totalPrice);
        }

        int totalDiscount = calculateTotalDiscount(calendar, order);
        GiftItem giftItem = GiftItem.fromTotalPrice(totalPrice);
        int totalBenefit = calcalateTotalBenefit(totalDiscount, giftItem);
        return EventDetailsDTO.createWithBenefit(calendar, orderItems, totalPrice, giftItem,
                DiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT.calculateDiscount(calendar, order),
                DiscountPolicy.WEEKDAY_DISCOUNT.calculateDiscount(calendar, order),
                DiscountPolicy.WEEKEND_DISCOUNT.calculateDiscount(calendar, order),
                DiscountPolicy.SPECIAL_DISCOUNT.calculateDiscount(calendar, order),
                totalDiscount, totalBenefit, EventBadge.fromTotalBenefit(totalBenefit));
    }

    private int calculateTotalDiscount(Calendar calendar, Order order) {
        int totalDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            totalDiscount += discountPolicy.calculateDiscount(calendar, order);
        }
        return totalDiscount;
    }

    private int calcalateTotalBenefit(int totalDiscount, GiftItem giftItem) {
        return totalDiscount + (giftItem.get().price() * giftItem.quantity());
    }
}
