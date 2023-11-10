package christmas.domain;

public enum EventBadge {
    NONE(0),
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000);

    private final int threshold;

    EventBadge(int threshold) {
        this.threshold = threshold;
    }

    public static EventBadge fromTotalBenefit(int totalBenefit) {
        if (totalBenefit >= SANTA.threshold) {
            return SANTA;
        }
        if (totalBenefit >= TREE.threshold) {
            return TREE;
        }
        if (totalBenefit >= STAR.threshold) {
            return STAR;
        }
        return NONE;
    }
}
