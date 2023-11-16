package christmas.domain;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int threshold;

    EventBadge(String name, int threshold) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
