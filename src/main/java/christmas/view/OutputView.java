package christmas.view;

import christmas.domain.GiftItem;
import christmas.dto.EventDetailsDTO;
import java.text.DecimalFormat;
import java.util.Calendar;

public class OutputView {
    private final DecimalFormat THOUSANDS_COMMA = new DecimalFormat("#,###");

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventDetails(EventDetailsDTO eventDetailsDTO, Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", month, date);
        printOrderItems(eventDetailsDTO);
        printTotalPrice(eventDetailsDTO);
        printGiftItem(eventDetailsDTO);
        printBenefits(eventDetailsDTO);
        printTotalBenefit(eventDetailsDTO);
        printNetPrice(eventDetailsDTO);
        printEventBadge(eventDetailsDTO);
    }

    private void printOrderItems(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<주문 메뉴>%n");
        eventDetailsDTO.getOrderItems().forEach(
                (key, value) -> System.out.printf("%s %s개%n", key.getName(), THOUSANDS_COMMA.format(value)));
        System.out.println();
    }

    private void printTotalPrice(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<할인 전 총주문 금액>%n");
        System.out.printf("%s원%n%n", THOUSANDS_COMMA.format(eventDetailsDTO.getTotalPrice()));
    }

    private void printGiftItem(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<증정 메뉴>%n");
        GiftItem giftItem = eventDetailsDTO.getGiftItem();
        if (giftItem == GiftItem.NONE) {
            System.out.printf("없음%n%n");
            return;
        }
        System.out.printf("%s %s개%n%n", giftItem.getItem().getName(),
                THOUSANDS_COMMA.format(giftItem.getQuantity()));
    }

    private void printBenefits(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<혜택 내역>%n");
        if (isNoBenefit(eventDetailsDTO)) {
            System.out.printf("없음%n%n");
            return;
        }
        printDiscounts(eventDetailsDTO);
        printGiftItemPrice(eventDetailsDTO);
        System.out.println();
    }

    private boolean isNoBenefit(EventDetailsDTO eventDetailsDTO) {
        return eventDetailsDTO.getTotalDiscount() == 0 && eventDetailsDTO.getGiftItem() == GiftItem.NONE;
    }

    private void printDiscounts(EventDetailsDTO eventDetailsDTO) {
        if (eventDetailsDTO.getChristmasDDayDiscount() != 0) {
            System.out.printf("크리스마스 디데이 할인: -%s원%n",
                    THOUSANDS_COMMA.format(eventDetailsDTO.getChristmasDDayDiscount()));
        }
        if (eventDetailsDTO.getWeekdayDiscount() != 0) {
            System.out.printf("평일 할인: -%s원%n",
                    THOUSANDS_COMMA.format(eventDetailsDTO.getWeekdayDiscount()));
        }
        if (eventDetailsDTO.getWeekendDiscount() != 0) {
            System.out.printf("주말 할인: -%s원%n",
                    THOUSANDS_COMMA.format(eventDetailsDTO.getWeekendDiscount()));
        }
        if (eventDetailsDTO.getSpecialDiscount() != 0) {
            System.out.printf("특별 할인: -%s원%n",
                    THOUSANDS_COMMA.format(eventDetailsDTO.getSpecialDiscount()));
        }
    }

    private void printGiftItemPrice(EventDetailsDTO eventDetailsDTO) {
        GiftItem giftItem = eventDetailsDTO.getGiftItem();
        if (giftItem != GiftItem.NONE) {
            System.out.printf("증정 이벤트: -%s원%n",
                    THOUSANDS_COMMA.format(giftItem.getItem().getPrice() * giftItem.getQuantity()));
        }
    }

    private void printTotalBenefit(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<총혜택 금액>%n");
        if (eventDetailsDTO.getTotalBenefit() == 0) {
            System.out.printf("0원%n%n");
            return;
        }
        System.out.printf("-%s원%n%n", THOUSANDS_COMMA.format(eventDetailsDTO.getTotalBenefit()));
    }

    private void printNetPrice(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<할인 후 예상 결제 금액>%n");
        System.out.printf("%s원%n%n",
                THOUSANDS_COMMA.format(eventDetailsDTO.getTotalPrice() - eventDetailsDTO.getTotalDiscount()));
    }

    private void printEventBadge(EventDetailsDTO eventDetailsDTO) {
        System.out.printf("<12월 이벤트 배지>%n");
        System.out.printf("%s%n", eventDetailsDTO.getEventBadge().getName());
    }
}
