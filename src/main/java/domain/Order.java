package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static final int MAX_MENU_COUNT = 20;

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        Menu.validateMenus(menus);
        this.menus = new HashMap<>(menus);
    }

}
