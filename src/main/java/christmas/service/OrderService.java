package christmas.service;

import christmas.domain.MenuItem;
import christmas.domain.Order;

public class OrderService {
    public Order takeOrder(String orderInput) {
        Order order = new Order();
        String[] menuEntries = orderInput.split(",");
        for (String menuEntry : menuEntries) {
            addItemToOrder(order, menuEntry);
        }
        order.finalizeOrder();
        return order;
    }

    private void addItemToOrder(Order order, String menuEntry) {
        String[] menuDetails = menuEntry.split("-");
        String menuName = menuDetails[0];
        int quantity = Integer.parseInt(menuDetails[1]);
        MenuItem menuItem = MenuItem.getByName(menuName);
        order.addItem(menuItem, quantity);
    }
}
