package christmas.controller;

import christmas.domain.Order;
import christmas.dto.EventDetailsDTO;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Calendar;

public class EventController {
    private final int YEAR = 2023;
    private final EventService eventService;
    private final OrderService orderService;
    private final InputView inputView;
    private final OutputView outputView;

    public EventController() {
        this.eventService = new EventService();
        this.orderService = new OrderService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        outputView.printWelcomeMessage();
        int date = inputView.readDate();
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, Calendar.DECEMBER, date);
        while (true) {
            try {
                String orderInput = inputView.readOrder();
                Order order = orderService.takeOrder(orderInput);
                EventDetailsDTO eventDetailsDTO = eventService.getEventDetails(order, calendar);
                outputView.printEventDetails(eventDetailsDTO, calendar);
                return;
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
