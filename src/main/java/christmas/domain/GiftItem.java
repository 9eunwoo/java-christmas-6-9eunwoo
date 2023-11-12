package christmas.domain;

public enum GiftItem {
    NONE(null, 0),
    CHAMPAGNE(MenuItem.CHAMPAGNE, 120_000);

    private final MenuItem item;
    private final int threshold;

    GiftItem(MenuItem item, int threshold) {
        this.item = item;
        this.threshold = threshold;
    }

    public MenuItem getItem() {
        return item;
    }

    public static GiftItem fromTotalPrice(int totalPrice) {
        if (totalPrice >= CHAMPAGNE.threshold) {
            return CHAMPAGNE;
        }
        return NONE;
    }
}
