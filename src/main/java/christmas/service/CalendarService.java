package christmas.service;

import java.util.Calendar;

public class CalendarService {
    public Calendar getValidCalendar(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        validateDate(calendar, date);
        calendar.set(Calendar.DATE, date);
        return calendar;
    }

    private void validateDate(Calendar calendar, int date) {
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (date < 1 || date > lastDayOfMonth) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 날짜는 1일부터 %d일 사이로 입력해 주세요.", lastDayOfMonth));
        }
    }
}
