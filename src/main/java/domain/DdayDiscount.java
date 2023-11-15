package domain;

public class DdayDiscount implements DiscountEvent{
    private static final int START_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT = 100;

    @Override
    public int discountAmount(Order order) {
        return order.applyEventDiscount(START_DISCOUNT, INCREASE_DISCOUNT);
    }

}
