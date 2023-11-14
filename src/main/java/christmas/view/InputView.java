package christmas.view;

import camp.nextstep.edu.missionutils.Console;
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
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String userInput = Console.readLine();
                validateNonZeroLeadingUpToTwoDigits(userInput);
                int date = Integer.parseInt(userInput);
                return getValidCalendar(date);
            } catch (IllegalArgumentException dateInputException) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                System.out.println(dateInputException.getMessage());
            }
        }
    }

    private void validateNonZeroLeadingUpToTwoDigits(String userInput) {
        if (!NON_ZERO_LEADING_UP_TO_TWO_DIGITS.matcher(userInput).matches()) {
            throw new IllegalArgumentException("[ERROR] 2자리 이하의 자연수만 입력 가능합니다.");
        }
    }

    private Calendar getValidCalendar(int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, MONTH, date);
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (date < 1 || date > lastDayOfMonth) {
            throw new IllegalArgumentException("[ERROR] 날짜는 1일부터 31일 사이로 입력해 주세요.");
        }
        return calendar;
    }

    public String readOrderInput() {
        while (true) {
            try {
                System.out.println(
                        "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String userInput = Console.readLine();
                validateOrderInput(userInput);
                return userInput;
            } catch (IllegalArgumentException orderInputException) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                System.out.println(orderInputException.getMessage());
            }
        }
    }

    private void validateOrderInput(String userInput) {
        if (!ORDER_FORM.matcher(userInput).matches()) {
            throw new IllegalArgumentException("[ERROR] 주문 형식이 올바르지 않습니다.");
        }
    }
}
