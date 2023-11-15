package domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static final int MIN_EVENT_AMOUNT = 10000;
    private static final int CHRISTMAS_DAY = 25;
    private static final int WEEKS = 7;
    private static final int FRIDAY = 1;
    private static final int SATURDAY = 2;
    private static final int SPECIALDAY = 3;
    private static final int MIN_AMOUNT_POSSIBLE_GIFT = 120000;

    private final Map<Menu, Integer> menus;
    private final int totalAmount;
    private final int date;

    public Order(Map<Menu, Integer> menus, int date) {
        Menu.validateMenus(menus);
        this.menus = new HashMap<>(menus);
        this.totalAmount = calculateTotalAmount();
        this.date = date;
    }

    public boolean isPossibleGift() {
        return this.totalAmount >= MIN_AMOUNT_POSSIBLE_GIFT;
    }

    public boolean isTotalAmountOver() {
        return this.totalAmount >= MIN_EVENT_AMOUNT;
    }

    private int calculateTotalAmount() {
        return menus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().calculateAmount(entry.getValue()))
                .sum();
    }

    public static Order createOrder(String customerInput, int date) {
        Map<Menu, Integer> customerMenus = createMenus(customerInput);
        return new Order(customerMenus, date);
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

    public int applyEventDiscount(int startDiscount, int increaseDiscount) {
        if (this.date <= CHRISTMAS_DAY) {
            return startDiscount + (this.date - 1) * increaseDiscount;
        }
        return 0;
    }

    public int applyDailyDiscount(int dailyDiscount) {
        if (this.date % WEEKS != FRIDAY && this.date % WEEKS != SATURDAY) {
            int dessertCount = this.menus.entrySet().stream()
                    .filter(entry -> entry.getKey().isDessert())
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return dailyDiscount * (int)dessertCount;
        }
        return 0;
    }

    public int applyWeekendDiscount(int weekendDiscount) {
        if (this.date % WEEKS == FRIDAY || this.date % WEEKS == SATURDAY) {
            long mainCount = this.menus.entrySet().stream()
                    .filter(entry -> entry.getKey().isMain())
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return weekendDiscount * (int)mainCount;
        }
        return 0;
    }

    public int applySpecialDiscount(int specialDiscount) {
        if (this.date == CHRISTMAS_DAY || this.date % WEEKS == SPECIALDAY) {
            return specialDiscount;
        }
        return 0;
    }

}
