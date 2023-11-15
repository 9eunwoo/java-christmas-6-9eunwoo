package christmas.view;

import christmas.domain.DiscountPolicy;
import christmas.domain.GiftItem;
import christmas.dto.EventDetailsDTO;
import java.util.Calendar;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventDetails(EventDetailsDTO eventDetailsDTO) {
        printEventPreviewMessage(eventDetailsDTO);
        printOrderItems(eventDetailsDTO);
        printTotalPrice(eventDetailsDTO);
        printGiftItem(eventDetailsDTO);
        printBenefits(eventDetailsDTO);
        printTotalBenefit(eventDetailsDTO);
        printNetPrice(eventDetailsDTO);
        printEventBadge(eventDetailsDTO);
    }

    private void printEventPreviewMessage(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n",
                eventDetailsDTO.calendar().get(Calendar.DATE));
    }

    private void printOrderItems(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<주문 메뉴>%n");
        eventDetailsDTO.orderItems().forEach(
                (menuName, quantity) -> System.out.printf("%s %,d개%n", menuName, quantity));
        System.out.println();
    }

    private void printTotalPrice(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<할인 전 총주문 금액>%n");
        System.out.printf("%,d원%n%n", eventDetailsDTO.totalPrice());
    }

    private void printGiftItem(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<증정 메뉴>%n");
        if (eventDetailsDTO.giftItem() == GiftItem.NONE) {
            System.out.printf("없음%n%n");
            return;
        }
        System.out.printf("%s %,d개%n%n", eventDetailsDTO.giftItem(), eventDetailsDTO.giftItem().quantity());
    }

    private void printBenefits(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<혜택 내역>%n");
        if (eventDetailsDTO.totalDiscount() == 0 && eventDetailsDTO.giftItem() == GiftItem.NONE) {
            System.out.printf("없음%n%n");
            return;
        }
        printDiscounts(eventDetailsDTO);
        printGiftItemPrice(eventDetailsDTO);
        System.out.println();
    }

    private void printDiscounts(EventDetailsDTO eventDetailsDTO) {
        if (eventDetailsDTO.christmasDDayDiscount() != 0) {
            System.out.printf("%s: -%,d원%n",
                    DiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT, eventDetailsDTO.christmasDDayDiscount());
        }
        if (eventDetailsDTO.weekdayDiscount() != 0) {
            System.out.printf("%s: -%,d원%n", DiscountPolicy.WEEKDAY_DISCOUNT, eventDetailsDTO.weekdayDiscount());
        }
        if (eventDetailsDTO.weekendDiscount() != 0) {
            System.out.printf("%s: -%,d원%n", DiscountPolicy.WEEKEND_DISCOUNT, eventDetailsDTO.weekendDiscount());
        }
        if (eventDetailsDTO.specialDiscount() != 0) {
            System.out.printf("%s: -%,d원%n", DiscountPolicy.SPECIAL_DISCOUNT, eventDetailsDTO.specialDiscount());
        }
    }

    private void printGiftItemPrice(EventDetailsDTO eventDetailsDTO) {
        GiftItem giftItem = eventDetailsDTO.giftItem();
        if (giftItem != GiftItem.NONE) {
            System.out.printf("증정 이벤트: -%,d원%n", giftItem.get().price() * giftItem.quantity());
        }
    }

    private void printTotalBenefit(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<총혜택 금액>%n");
        if (eventDetailsDTO.totalBenefit() == 0) {
            System.out.printf("없음%n%n");
            return;
        }
        System.out.printf("-%,d원%n%n", eventDetailsDTO.totalBenefit());
    }

    private void printNetPrice(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<할인 후 예상 결제 금액>%n");
        System.out.printf("%,d원%n%n", eventDetailsDTO.totalPrice() - eventDetailsDTO.totalDiscount());
    }

    private void printEventBadge(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<12월 이벤트 배지>%n");
        System.out.printf("%s%n", eventDetailsDTO.eventBadge());
    }
}
