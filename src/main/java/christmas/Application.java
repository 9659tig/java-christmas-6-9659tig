package christmas;

import domain.*;
import view.InputView;


public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        int date = inputView.readDate();
        Order order = inputView.readOrder(date);

        Benefits benefits = new Benefits(order);
        System.out.println("총 혜택 금액: " + benefits.getTotalBenefitsAmount());
        String badgeName = benefits.getBadgeName();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }
}
