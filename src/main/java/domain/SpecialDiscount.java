package domain;

public class SpecialDiscount implements DiscountEvent {
    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public int discountAmount(Order order) {
        return order.applySpecialDiscount(SPECIAL_DISCOUNT);
    }
}
