package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Message;
import java.util.regex.Pattern;
import java.util.Calendar;

public class InputView {
    private final Pattern NON_ZERO_LEADING_NUMBER = Pattern.compile("^[1-9][0-9]*$");
    private final Pattern ORDER_FORM = Pattern.compile("^[가-힣]+-[1-9][0-9]*(,[가-힣]+-[1-9][0-9]*)*$");
    private final int YEAR = 2023;
    private final int MONTH = 12;

    public int readDate() {
        while (true) {
            try {
                System.out.println(Message.PROMPT_DATE.getMessage());
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

    public String readOrder() {
        while (true) {
            try {
                System.out.println(Message.PROMPT_ORDER.getMessage());
                String userInput = Console.readLine();
                validateOrder(userInput);
                return userInput;
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

    private void validateOrder(String userInput) {
        if (!ORDER_FORM.matcher(userInput).matches()) {
            throw new IllegalArgumentException(Message.ERROR_INVALID_ORDER.getMessage());
        }
    }
}
