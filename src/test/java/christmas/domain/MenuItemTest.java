package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MenuItemTest {
    @ParameterizedTest
    @EnumSource(MenuItem.class)
    public void givenMenuItem_whenGetDetails_thenCorrectDetailsReturned(MenuItem menuItem) {
        // when
        String nameInKorean = menuItem.getNameInKorean();
        int price = menuItem.getPrice();
        MenuCategory category = menuItem.getCategory();

        // then
        if (menuItem == MenuItem.MUSHROOM_SOUP) {
            assertThat(nameInKorean).isEqualTo("양송이수프");
            assertThat(price).isEqualTo(6_000);
            assertThat(category).isEqualTo(MenuCategory.APPETIZER);
        }
        if (menuItem == MenuItem.TBONE_STEAK) {
            assertThat(nameInKorean).isEqualTo("티본스테이크");
            assertThat(price).isEqualTo(55_000);
            assertThat(category).isEqualTo(MenuCategory.MAIN_DISH);
        }
        if (menuItem == MenuItem.CHOCO_CAKE) {
            assertThat(nameInKorean).isEqualTo("초코케이크");
            assertThat(price).isEqualTo(15_000);
            assertThat(category).isEqualTo(MenuCategory.DESSERT);
        }
        if (menuItem == MenuItem.ZERO_COLA) {
            assertThat(nameInKorean).isEqualTo("제로콜라");
            assertThat(price).isEqualTo(3_000);
            assertThat(category).isEqualTo(MenuCategory.BEVERAGE);
        }
    }
}
