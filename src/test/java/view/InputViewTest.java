package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputViewTest {
    private InputView inputView;

    @BeforeEach
    void setting() {
        inputView = new InputView();
    }

    @DisplayName("정상적인 날짜 입력")
    @Test
    void readDate_success() {
        assertDoesNotThrow(() -> inputView.validateDate("15"));
    }

    @DisplayName("날짜 범위 초과")
    void readDate_limitDateRange() {
        assertThrows(IllegalArgumentException.class, () -> inputView.validateDate("32"));
    }

    @DisplayName("날짜 입력 형식 위반")
    void readDate_dateType() {
        assertThrows(IllegalArgumentException.class, () -> inputView.validateDate("abc"));
    }

}