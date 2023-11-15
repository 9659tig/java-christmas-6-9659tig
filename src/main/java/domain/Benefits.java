package domain;

public class Benefits {
    private static final int GIFT_AMOUNT = 25000;
    private int dDayDiscountAmount = 0;
    private int dailyDiscountAmount = 0;
    private int weekendDiscountAmount = 0;
    private int specialDiscountAmount = 0;
    private int giftAmount = 0;

    public Benefits(Order order) {
        if (order.isTotalAmountOver()) {
            calculateDiscount(order);
        }
        this.dDayDiscountAmount = dDayDiscountAmount;
        this.dailyDiscountAmount = dailyDiscountAmount;
        this.weekendDiscountAmount = weekendDiscountAmount;
        this.specialDiscountAmount = specialDiscountAmount;
        this.giftAmount = giftAmount;

        if (order.isPossibleGift()) {
            giveGift();
        }
    }

    private void calculateDiscount(Order order) {
        dDayDiscountAmount = new DdayDiscount().discountAmount(order);
        dailyDiscountAmount = new DailyDiscount().discountAmount(order);
        weekendDiscountAmount = new WeekendDiscount().discountAmount(order);
        specialDiscountAmount = new SpecialDiscount().discountAmount(order);
    }

    private void giveGift() {
        this.giftAmount = GIFT_AMOUNT;
    }
    public int getTotalBenefitsAmount() {
        return dDayDiscountAmount + dailyDiscountAmount + weekendDiscountAmount + specialDiscountAmount + giftAmount;
    }

    public String getBadgeName() {
        EventBadge badge = EventBadge.getBadge(getTotalBenefitsAmount());
        return EventBadge.getNameOrEmpty(badge);
    }
}
