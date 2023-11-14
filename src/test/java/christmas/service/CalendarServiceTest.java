package christmas.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalendarServiceTest {
    @DisplayName("유효하지 않은 날짜를 입력할 경우, 예외를 발생시키는지 테스트")
    @Test
    void createValidCalendar_ShouldThrowException_WhenInvalidDate() {
        // given
        int year = 2023;
        int month = 12;
        int date = 32;
        CalendarService calendarService = new CalendarService();

        // when & then
        assertThatThrownBy(() -> calendarService.createValidCalendar(year, month, date))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
