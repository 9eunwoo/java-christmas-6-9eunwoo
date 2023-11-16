package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern TWO_DIGIT_NUMBER = Pattern.compile("^[1-9][0-9]?$");
    private static final Pattern ORDER_FORM = Pattern.compile("^[가-힣]+-[1-9][0-9]*(,[가-힣]+-[1-9][0-9]*)*$");

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String userInput = Console.readLine();
        validateTwoDigitNumber(userInput);
        int date = Integer.parseInt(userInput);
        return date;
    }

    private void validateTwoDigitNumber(String userInput) {
        if (!TWO_DIGIT_NUMBER.matcher(userInput).matches()) {
            throw new IllegalArgumentException("[ERROR] 2자리 이하의 숫자만 입력 가능합니다.");
        }
    }

    public String readOrderForm() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String userInput = Console.readLine();
        validateOrderForm(userInput);
        return userInput;
    }

    private void validateOrderForm(String userInput) {
        if (!ORDER_FORM.matcher(userInput).matches()) {
            throw new IllegalArgumentException("[ERROR] 주문 입력 형식이 올바르지 않습니다.");
        }
    }
}
