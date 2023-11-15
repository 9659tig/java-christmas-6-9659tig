package view;

import camp.nextstep.edu.missionutils.Console;
import domain.Order;

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
                return Order.createOrder(customerInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

