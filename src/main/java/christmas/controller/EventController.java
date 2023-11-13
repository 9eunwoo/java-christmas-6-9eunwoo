package christmas.controller;

import christmas.domain.Order;
import christmas.dto.EventDetailsDTO;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Calendar;

public class EventController {
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
        Calendar calendar = inputView.getCalendarForInputDate();

        Order order = handleOrderCreation();

        EventDetailsDTO eventDetailsDTO = eventService.getEventDetails(calendar, order);
        outputView.printEventDetails(eventDetailsDTO);
    }

    private Order handleOrderCreation() {
        while (true) {
            try {
                String orderInput = inputView.readOrderInput();
                return orderService.createOrder(orderInput);
            } catch (IllegalArgumentException orderAddtionException) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                System.out.println(orderAddtionException.getMessage());
            } catch (IllegalStateException orderFinalizationException) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                System.out.println(orderFinalizationException.getMessage());
            }
        }
    }
}
