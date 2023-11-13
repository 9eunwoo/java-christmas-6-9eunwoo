package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Message;
import java.util.regex.Pattern;
import java.util.Calendar;

public class InputView {
    private static final int YEAR = 2023;
    private static final int MONTH = Calendar.DECEMBER;
    private static final Pattern NON_ZERO_LEADING_UP_TO_TWO_DIGITS = Pattern.compile("^[1-9][0-9]?$");
    private static final Pattern ORDER_FORM = Pattern.compile("^[가-힣]+-[1-9][0-9]*(,[가-힣]+-[1-9][0-9]*)*$");

    public Calendar getCalendarForInputDate() {
        while (true) {
            try {
                System.out.println(Message.PROMPT_DATE.getMessage());
                String userInput = Console.readLine();
                validateNonZeroLeadingUpToTwoDigits(userInput);
                int date = Integer.parseInt(userInput);
                return getValidCalendar(date);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public String readOrderInput() {
        while (true) {
            try {
                System.out.println(Message.PROMPT_ORDER.getMessage());
                String userInput = Console.readLine();
                validateOrderInput(userInput);
                return userInput;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void validateNonZeroLeadingUpToTwoDigits(String userInput) {
        if (!NON_ZERO_LEADING_UP_TO_TWO_DIGITS.matcher(userInput).matches()) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_DATE.getMessage());
        }
    }

    private Calendar getValidCalendar(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, MONTH, date);
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (date < 1 || date > lastDayOfMonth) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_DATE.getMessage());
        }
        return calendar;
    }

    private void validateOrderInput(String userInput) {
        if (!ORDER_FORM.matcher(userInput).matches()) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_ORDER.getMessage());
        }
    }
}
