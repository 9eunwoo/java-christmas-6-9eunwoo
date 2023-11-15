package christmas.domain;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Optional;

public enum MenuItem {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000, Category.APPETIZER),
    TAPAS("타파스", 5_500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Category.APPETIZER),
    // 메인
    TBONE_STEAK("티본스테이크", 55_000, Category.MAIN),
    BBQ_RIB("바비큐립", 54_000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Category.MAIN),
    // 디저트
    CHOCO_CAKE("초코케이크", 15_000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Category.DESSERT),
    // 음료
    ZERO_COLA("제로콜라", 3_000, Category.BEVERAGE),
    RED_WINE("레드와인", 60_000, Category.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Category.BEVERAGE),
    // 없음
    NONE("*없음*", 0, Category.UNKNOWN);

    private final String name;
    private final int price;
    private final Category category;

    MenuItem(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int price() {
        return price;
    }

    public Category category() {
        return category;
    }

    private static final Map<String, MenuItem> stringToEnum = Arrays.stream(values())
            .collect(Collectors.toMap(Object::toString, menuItem -> menuItem));

    public static MenuItem fromString(String name) {
        return Optional.ofNullable(stringToEnum.get(name))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다: " + name));
    }

    @Override
    public String toString() {
        return name;
    }
}
