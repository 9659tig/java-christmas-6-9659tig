package christmas;

import domain.*;
import view.InputView;
import view.OutputView;


public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        int date = inputView.readDate();
        Order order = inputView.readOrder(date);
        Benefits benefits = new Benefits(order);

        OutputView outputView = new OutputView();
        outputView.printPreview(date);
        outputView.printMenuAndTotalAmountAndGift(order);
        outputView.printDiscountDetails(order, benefits);
    }
}
