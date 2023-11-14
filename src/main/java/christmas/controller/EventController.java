package christmas.controller;

import christmas.domain.Order;
import christmas.dto.EventDetailsDTO;
import christmas.service.CalendarService;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Calendar;

public class EventController {
    private static final int YEAR = 2023;
    private static final int MONTH = Calendar.DECEMBER;

    private final CalendarService calendarService;
    private final EventService eventService;
    private final OrderService orderService;
    private final InputView inputView;
    private final OutputView outputView;

    public EventController() {
        this.calendarService = new CalendarService();
        this.orderService = new OrderService();
        this.eventService = new EventService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        outputView.printWelcomeMessage();
        Calendar calendar = createValidCalendarFromUserInput();
        Order order = createValidOrderFromUserInput();
        EventDetailsDTO eventDetailsDTO = eventService.createEventDetails(calendar, order);
        outputView.printEventDetails(eventDetailsDTO);
    }

    private Calendar createValidCalendarFromUserInput() {
        while (true) {
            try {
                int date = inputView.readDate();
                return calendarService.createValidCalendar(YEAR, MONTH, date);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                System.out.println(e.getMessage());
            }
        }
    }

    private Order createValidOrderFromUserInput() {
        while (true) {
            try {
                String orderForm = inputView.readOrderForm();
                return orderService.createValidOrder(orderForm);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                System.out.println(e.getMessage());
            }
        }
    }
}
