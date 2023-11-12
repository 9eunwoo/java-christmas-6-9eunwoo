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

    public EventDetailsDTO getEventDetails(Order order, Calendar calendar) {
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        int totalPrice = order.getTotalPrice();
        EventDetailsDTO.Builder builder = new EventDetailsDTO.Builder(orderItems, totalPrice);
        if (totalPrice < EVENT_THRESHOLD) {
            return builder.build();
        }
        int totalDiscount = calculateTotalDiscount(order, calendar);
        GiftItem giftItem = GiftItem.fromTotalPrice(totalPrice);
        int totalBenefit = totalDiscount + (giftItem.getItem().getPrice() * giftItem.getQuantity());
        return builder.christmasDDayDiscount(DiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT.calculateDiscount(order, calendar))
                .weekdayDiscount(DiscountPolicy.WEEKDAY_DISCOUNT.calculateDiscount(order, calendar))
                .weekendDiscount(DiscountPolicy.WEEKEND_DISCOUNT.calculateDiscount(order, calendar))
                .specialDiscount(DiscountPolicy.SPECIAL_DISCOUNT.calculateDiscount(order, calendar))
                .giftItem(giftItem).totalBenefit(totalBenefit).eventBadge(EventBadge.fromTotalBenefit(totalBenefit))
                .build();
    }

    private int calculateTotalDiscount(Order order, Calendar calendar) {
        int totalDiscount = 0;
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            totalDiscount += discountPolicy.calculateDiscount(order, calendar);
        }
        return totalDiscount;
    }
}
