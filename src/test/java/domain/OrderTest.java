package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("정상적인 주문")
    @Test
    void createOrder_success() {
        assertDoesNotThrow(() -> Order.createOrder("해산물파스타-2,레드와인-1,초코케이크-1"));
    }

    @DisplayName("주문 입력 형식 위반")
    @Test
    void createOrder_invalidInputType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Order.createOrder("해산물파스타"));
        assertEquals("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("중복된 메뉴 주문")
    @Test
    void createOrder_menuDuplicate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Order.createOrder("해산물파스타-2,해산물파스타-1"));
        assertEquals("[ERROR] 중복된 메뉴가 있습니다. 다시 입력해 주세요.", exception.getMessage());
    }

}