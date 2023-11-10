package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MenuItemTest {
    @DisplayName("메뉴 각 항목의 이름, 가격, 카테고리를 정확히 반환하는지 테스트")
    @ParameterizedTest
    @EnumSource(MenuItem.class)
    public void givenMenuItem_whenGetDetails_thenCorrectDetailsReturned(MenuItem menuItem) {
        // when
        String name = menuItem.getName();
        int price = menuItem.getPrice();
        Category category = menuItem.getCategory();

        // then
        if (menuItem == MenuItem.MUSHROOM_SOUP) {
            assertThat(name).isEqualTo("양송이수프");
            assertThat(price).isEqualTo(6_000);
            assertThat(category).isEqualTo(Category.APPETIZER);
        }
        if (menuItem == MenuItem.TBONE_STEAK) {
            assertThat(name).isEqualTo("티본스테이크");
            assertThat(price).isEqualTo(55_000);
            assertThat(category).isEqualTo(Category.MAIN);
        }
        if (menuItem == MenuItem.CHOCO_CAKE) {
            assertThat(name).isEqualTo("초코케이크");
            assertThat(price).isEqualTo(15_000);
            assertThat(category).isEqualTo(Category.DESSERT);
        }
        if (menuItem == MenuItem.ZERO_COLA) {
            assertThat(name).isEqualTo("제로콜라");
            assertThat(price).isEqualTo(3_000);
            assertThat(category).isEqualTo(Category.BEVERAGE);
        }
    }
}
