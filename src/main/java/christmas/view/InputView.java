package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Message;
import java.util.regex.Pattern;
import java.util.Calendar;

public class InputView {
    private final Pattern NON_ZERO_LEADING_NUMBER = Pattern.compile("^[1-9][0-9]*$");
    private final int YEAR = 2023;
    private final int MONTH = 12;

    public int readDate() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String userInput = Console.readLine();
                validateNonZeroLeadingNumber(userInput);
                int date = Integer.parseInt(userInput);
                validateDate(YEAR, MONTH, date);
                return date;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void validateNonZeroLeadingNumber(String userInput) {
        if (!NON_ZERO_LEADING_NUMBER.matcher(userInput).matches()) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_ORDER.getMessage());
        }
    }

    private void validateDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (date < 1 || date > lastDayOfMonth) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_DATE.getMessage());
        }
    }
}
