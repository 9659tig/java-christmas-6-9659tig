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

    public int getTotalBenefitsAmountExceptGift() {
        return dDayDiscountAmount + dailyDiscountAmount + weekendDiscountAmount + specialDiscountAmount;
    }

    public String getBadgeName() {
        EventBadge badge = EventBadge.getBadge(getTotalBenefitsAmount());
        return EventBadge.getNameOrEmpty(badge);
    }

    @Override
    public String toString() {
        if (dDayDiscountAmount == 0 && dailyDiscountAmount == 0 && specialDiscountAmount == 0 && giftAmount == 0) {
            return "없음\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("크리스마스 디데이 할인: -").append(String.format("%,d", dDayDiscountAmount)).append("원\n");
        sb.append("평일 할인: -").append(String.format("%,d", dailyDiscountAmount)).append("원\n");
        sb.append("특별 할인: -").append(String.format("%,d", specialDiscountAmount)).append("원\n");
        sb.append("증정 이벤트: -").append(String.format("%,d", giftAmount)).append("원\n");
        return sb.toString();
    }
}
