package view;

import domain.Benefits;
import domain.Order;

public class OutputView {
    private static final String NO_BENEFIT = "없음";

    public void printPreview(int date) {
        System.out.println(date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
    }

    public void printMenuAndTotalAmountAndGift(Order order) {
        System.out.println("<주문 메뉴>");
        System.out.println(order+"\n");
        printGift(order);
    }

    private void printGift(Order order) {
        String outputText = NO_BENEFIT;
        System.out.println("<증정 메뉴>");
        if (order.isPossibleGift()){
            outputText = "샴페인 1개";
        }
        System.out.println(outputText+"\n");
    }

    public void printDiscountDetails(Order order, Benefits benefits) {
        System.out.println("<혜택 내역>");
        System.out.println(benefits.toString());

        System.out.println("<총혜택 금액>");
        System.out.println(String.format("%,d",benefits.getTotalBenefitsAmountExceptGift())+"원\n");

        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d",order.calculateFinalPayment(benefits))+"원\n");

        System.out.println("<12월 이벤트 배지>");
        System.out.println(benefits.getBadgeName());
    }
}
