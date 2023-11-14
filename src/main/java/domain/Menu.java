package domain;

import java.util.Arrays;
import java.util.Map;

public enum Menu {
    양송이수프(6000, "애피타이저"),
    타파스(5500, "애피타이저"),
    시저샐러드(8000, "애피타이저"),
    티본스테이크(55000, "메인"),
    바비큐립(54000, "메인"),
    해산물파스타(35000, "메인"),
    크리스마스파스타(25000, "메인"),
    초코케이크(15000, "디저트"),
    아이스크림(5000, "디저트"),
    제로콜라(3000, "음료"),
    레드와인(60000, "음료"),
    샴페인(25000, "음료");

    private final int price;
    private final String type;
    private static final int MAX_MENU_COUNT = 20;
    private static final int MIN_MENU_COUNT = 1;

    Menu(int price, String type) {
        this.price = price;
        this.type = type;
    }

    public static void validateMenus(Map<Menu, Integer> menus) {
        int totalMenuCount = menus.values().stream().mapToInt(Integer::intValue).sum();
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        }
        if (menus.keySet().stream().allMatch(menu -> menu.type == "음료")) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public static int validateAndReturnMenuCount(String count) {
        try {
            int cnt = Integer.parseInt(count);
            if (cnt > MAX_MENU_COUNT) {
                throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
            }
            if(cnt < MIN_MENU_COUNT){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            return cnt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static Menu of(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }
}
