package christmas.dto;

import christmas.domain.EventBadge;
import christmas.domain.GiftItem;
import christmas.domain.MenuItem;
import java.util.Map;

public class EventDetailsDTO {
    private final Map<MenuItem, Integer> orderItems;
    private final int totalPrice;
    private final GiftItem giftItem;
    private final int christmasDDayDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final int totalBenefit;
    private final EventBadge eventBadge;

    private EventDetailsDTO(Builder builder) {
        this.orderItems = builder.orderItems;
        this.totalPrice = builder.totalPrice;
        this.giftItem = builder.giftItem;
        this.christmasDDayDiscount = builder.christmasDDayDiscount;
        this.weekdayDiscount = builder.weekdayDiscount;
        this.weekendDiscount = builder.weekendDiscount;
        this.specialDiscount = builder.specialDiscount;
        this.totalBenefit = builder.totalBenefit;
        this.eventBadge = builder.eventBadge;
    }

    public Map<MenuItem, Integer> getOrderItems() {
        return orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public GiftItem getGiftItem() {
        return giftItem;
    }

    public int getChristmasDDayDiscount() {
        return christmasDDayDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getTotalBenefit() {
        return totalBenefit;
    }

    public EventBadge getEventBadge() {
        return eventBadge;
    }

    public static class Builder {
        private final Map<MenuItem, Integer> orderItems;
        private final int totalPrice;

        private GiftItem giftItem = GiftItem.NONE;
        private int christmasDDayDiscount = 0;
        private int weekdayDiscount = 0;
        private int weekendDiscount = 0;
        private int specialDiscount = 0;
        private int totalBenefit = 0;
        private EventBadge eventBadge = EventBadge.NONE;

        public Builder(Map<MenuItem, Integer> orderItems, int totalPrice) {
            this.orderItems = orderItems;
            this.totalPrice = totalPrice;
        }

        public Builder giftItem(GiftItem giftItem) {
            this.giftItem = giftItem;
            return this;
        }

        public Builder christmasDDayDiscount(int discount) {
            this.christmasDDayDiscount = discount;
            return this;
        }

        public Builder weekdayDiscount(int discount) {
            this.weekdayDiscount = discount;
            return this;
        }

        public Builder weekendDiscount(int discount) {
            this.weekendDiscount = discount;
            return this;
        }

        public Builder specialDiscount(int discount) {
            this.specialDiscount = discount;
            return this;
        }

        public Builder totalBenefit(int benefit) {
            this.totalBenefit = benefit;
            return this;
        }

        public Builder eventBadge(EventBadge badge) {
            this.eventBadge = badge;
            return this;
        }

        public EventDetailsDTO build() {
            return new EventDetailsDTO(this);
        }
    }
}