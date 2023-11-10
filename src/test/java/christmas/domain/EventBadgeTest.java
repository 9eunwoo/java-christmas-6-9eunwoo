package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {
    @DisplayName("총 혜택 금액에 따라 이벤트 배지가 정확히 부여되는지 확인한다.")
    @Test
    void givenTotalBenefit_whenGetEventBadge_thenCorrectEventBadgeReturned() {
        // given
        int totalBenefit = 20_000;
        EventBadge expectedEventBadge = EventBadge.SANTA;
        // when
        EventBadge actualEventBadge = EventBadge.getEventBadge(totalBenefit);
        // then
        assertThat(actualEventBadge).isEqualTo(expectedEventBadge);
    }   
}
