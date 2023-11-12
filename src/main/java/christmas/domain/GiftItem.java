package christmas.domain;

public enum GiftItem {
    NONE(null, 0, 0),
    CHAMPAGNE(MenuItem.CHAMPAGNE, 1, 120_000);

    private final MenuItem item;
    private final int quantity;
    private final int threshold;

    GiftItem(MenuItem item, int quantity, int threshold) {
        this.item = item;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public static GiftItem fromTotalPrice(int totalPrice) {
        if (totalPrice >= CHAMPAGNE.threshold) {
            return CHAMPAGNE;
        }
        return NONE;
    }
}