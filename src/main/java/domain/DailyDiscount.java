package domain;

public class DailyDiscount implements DiscountEvent {
    private static final int DAILY_DISCOUNT = 2023;

    @Override
    public int discountAmount(Order order) {
        return order.applyDailyDiscount(DAILY_DISCOUNT);
    }
}