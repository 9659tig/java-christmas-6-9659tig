package view;
import camp.nextstep.edu.missionutils.Console;
import domain.Menu;
import domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        while(true){
            String input = Console.readLine();
            try{
                int date = validateDate(input);
                return date;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            return date;
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public Order readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while (true) {
            try {
                String customerInput = Console.readLine();
                Map<Menu, Integer> customerMenus = createMenus(customerInput);

                return new Order(customerMenus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<Menu, Integer> createMenus(String input) {
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

