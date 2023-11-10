package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuItemTest {
    @DisplayName("메뉴 각 항목의 이름, 가격, 카테고리를 정확히 반환하는지 테스트")
    @Test
    public void givenMenuItem_whenGetDetails_thenCorrectDetailsReturned() {
        // given
        String expected = """
                <애피타이저>
                양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

                <메인>
                티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

                <디저트>
                초코케이크(15,000), 아이스크림(5,000)

                <음료>
                제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
                    """;
        // when
        StringBuilder sb = new StringBuilder();
        for (Category category : Category.values()) {
            sb.append("<").append(category.getName()).append(">\n");
            for (MenuItem item : MenuItem.values()) {
                if (item.getCategory() == category) {
                    String formattedPrice = String.format(Locale.KOREA, "%,d", item.getPrice());
                    sb.append(item.getName()).append("(").append(formattedPrice).append("), ");
                }
            }
            // 마지막에 나오는 ", "를 제거하기 위해 길이에서 2를 뺌
            int length = sb.length();
            sb.delete(length - 2, length).append("\n\n");
        }
        // 마지막에 나오는 "\n"를 제거하기 위해 길이에서 1을 뺌
        sb.delete(sb.length() - 1, sb.length());
        String actual = sb.toString();
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
