package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GiftItemTest {
    @DisplayName("총 주문 금액에 따라 증정 메뉴가 정확히 부여되는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            // given
            "0, NONE",
            "119_999, NONE",
            "120_000, CHAMPAGNE",
    })
    void givenTotalPrice_whenGetGiftItem_thenCorrectGiftItemReturned(int totalPrice, GiftItem expectedGiftItem) {
        // when
        GiftItem actualGiftItem = GiftItem.fromTotalPrice(totalPrice);
        // then
        assertThat(actualGiftItem).isEqualTo(expectedGiftItem);
    }
}
