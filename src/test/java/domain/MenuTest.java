package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Map<Menu, Integer> menus = new HashMap<>();
    @BeforeEach
    void setting(){
        menus.clear();
    }

    @DisplayName("메뉴최대개수제한")
    @Test
    void validateMenus_limitMaximum() {
        menus.put(Menu.양송이수프, 1);
        menus.put(Menu.타파스, 1);
        menus.put(Menu.제로콜라, 19);

        Exception exception  = assertThrows(IllegalArgumentException.class, () -> Menu.validateMenus(menus));
        assertEquals("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.", exception.getMessage());
    }
    @DisplayName("정상적인 메뉴 input")
    @Test
    void validateMenus_pass(){
        menus.put(Menu.양송이수프, 1);
        menus.put(Menu.제로콜라, 1);

        assertDoesNotThrow(() -> Menu.validateMenus(menus));
    }

    @DisplayName("음료만주문불가")
    @Test
    void validateMenus_limitOnlyDrinks(){
        menus.put(Menu.제로콜라, 1);
        menus.put(Menu.레드와인, 1);

        Exception exception  = assertThrows(IllegalArgumentException.class, () -> Menu.validateMenus(menus));
        assertEquals("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("한 메뉴 최대 개수 제한")
    @Test
    void validateAndReturnMenuCount_limitMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Menu.validateAndReturnMenuCount("21"));
        assertEquals("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.", exception.getMessage());
    }

    @DisplayName("한 메뉴 최소 개수 제한")
    @Test
    void validateAndReturnMenuCount_limitMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Menu.validateAndReturnMenuCount("0"));
        assertEquals("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("메뉴 개수가 숫자가 아닐경우")
    @Test
    void validateAndReturnMenuCount_notInt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Menu.validateAndReturnMenuCount("abc"));
        assertEquals("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.", exception.getMessage());
    }

    @DisplayName("정상적인 메뉴 input 및 리턴")
    @Test
    void validateAndReturnMenuCount_success() {
        assertEquals(1, Menu.validateAndReturnMenuCount("1"));
        assertEquals(20, Menu.validateAndReturnMenuCount("20"));
    }

    @DisplayName("메뉴판에 없는 메뉴")
    @Test
    void isExistAndReturnMenu_noneMenu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Menu.isExistAndReturnMenu("존재하지않는메뉴"));
        assertEquals("[ERROR] 존재하지 않는 메뉴입니다.", exception.getMessage());
    }

    @DisplayName("메뉴판에 있는 메뉴")
    @Test
    void isExistAndReturnMenu_success() {
        assertEquals(Menu.양송이수프, Menu.isExistAndReturnMenu("양송이수프"));
        assertEquals(Menu.타파스, Menu.isExistAndReturnMenu("타파스"));
    }

}