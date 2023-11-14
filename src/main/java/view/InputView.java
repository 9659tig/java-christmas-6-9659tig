package view;
import camp.nextstep.edu.missionutils.Console;
import domain.Menu;

import java.util.ArrayList;
import java.util.List;


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

    public List<Menu> readMenus() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while(true){
            try{
                String input = Console.readLine();
                List<Menu> menus = parseMenus(input);
                return menus;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Menu> parseMenus(String input) {
        String[] menuInputs = input.split(",");
        List<Menu> menus = new ArrayList<>();
        for (String menuInput : menuInputs) {
            String[] menu = menuInput.split("-");
            String menuName = menu[0];
            int menuCnt = validMenuMinLimitAndInt(menu[1]);
            menus.add(new Menu(menuName, menuCnt));
        }
        return menus;
    }


    private static int validMenuMinLimitAndInt(String menuNum) {
        try {
            int cnt  = Integer.parseInt(menuNum);
            if (cnt < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            if (cnt > 20){
                throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
            }
            return cnt;
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}

