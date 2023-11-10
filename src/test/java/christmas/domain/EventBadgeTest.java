package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventBadgeTest {
    @DisplayName("총 혜택 금액에 따라 이벤트 배지가 정확히 부여되는지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            // given
            "0, NONE",
            "4_999, NONE",
            "5_000, STAR",
            "9_999, STAR",
            "10_000, TREE",
            "19_999, TREE",
            "20_000, SANTA"
    })
    void givenTotalBenefit_whenGetEventBadge_thenCorrectEventBadgeReturned(int totalBenefit,
            EventBadge expectedEventBadge) {
        // when
        EventBadge actualEventBadge = EventBadge.fromTotalBenefit(totalBenefit);
        // then
        assertThat(actualEventBadge).isEqualTo(expectedEventBadge);
    }
}
