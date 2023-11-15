package domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static final int MAX_MENU_COUNT = 20;

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        Menu.validateMenus(menus);
        this.menus = new HashMap<>(menus);
    }

    public static Order createOrder(String customerInput) {
        Map<Menu, Integer> customerMenus = createMenus(customerInput);
        return new Order(customerMenus);
    }

    private static Map<Menu, Integer> createMenus(String input) {
        String[] menus = input.split(",");
        Map<Menu, Integer> customerMenus = new HashMap<>();

        for (String menu : menus) {
            String[] parsedMenu = menu.split("-");
            validOrderForm(parsedMenu);

            Menu customerMenu = Menu.isExistAndReturnMenu(parsedMenu[0]);
            validOrderDuplicate(customerMenus, customerMenu);

            int menuCnt = Menu.validateAndReturnMenuCount(parsedMenu[1]);
            customerMenus.put(customerMenu, menuCnt);
        }
        return customerMenus;
    }

    private static void validOrderForm(String[] parsedMenu) {
        if (parsedMenu.length < 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validOrderDuplicate(Map<Menu, Integer> menus, Menu menu) {
        if (menus.containsKey(menu)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴가 있습니다. 다시 입력해 주세요.");
        }
    }

}
