package domain;

public class WeekendDiscount implements DiscountEvent {
    private static final int WEEKEND_DISCOUNT = 2023;

    @Override
    public int discountAmount(Order order) {
        return order.applyWeekendDiscount(WEEKEND_DISCOUNT);
    }
}
