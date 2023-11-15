package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Map<Menu, Integer> menus = new HashMap<>();

    @DisplayName("정상적인 주문")
    @Test
    void createOrder_success() {
        assertDoesNotThrow(() -> Order.createOrder("해산물파스타-2,레드와인-1,초코케이크-1",1));
    }

    @DisplayName("주문 입력 형식 위반")
    @Test
    void createOrder_invalidInputType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Order.createOrder("해산물파스타",1));
        assertEquals("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("중복된 메뉴 주문")
    @Test
    void createOrder_menuDuplicate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Order.createOrder("해산물파스타-2,해산물파스타-1",1));
        assertEquals("[ERROR] 중복된 메뉴가 있습니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("주문 총액 계산 - 혜택 가능")
    @Test
    void calculateTotalAmount_eventPossible() {
        menus.clear();
        menus.put(Menu.티본스테이크, 2);
        menus.put(Menu.바비큐립, 1);
        menus.put(Menu.제로콜라, 3);

        Order order = new Order(menus,1);
        assertTrue(order.isTotalAmountOver());
    }

    @DisplayName("주문 총액 계산 - 혜택 불가능")
    @Test
    void calculateTotalAmount_eventImpossible() {
        menus.clear();
        menus.put(Menu.타파스, 1);
        menus.put(Menu.제로콜라, 1);

        Order order = new Order(menus,1);
        assertFalse(order.isTotalAmountOver());
    }
}