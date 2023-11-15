package domain;

public enum EventBadge {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타");

    private final int minBenefits;
    private final String name;

    EventBadge(int minBenefits, String name) {
        this.minBenefits = minBenefits;
        this.name = name;
    }

    public static EventBadge getBadge(int totalBenefitsAmount) {
        EventBadge badge = null;
        for (EventBadge b : EventBadge.values()) {
            if (totalBenefitsAmount >= b.minBenefits) {
                badge = b;
            }
        }
        return badge;
    }

    public static String getNameOrEmpty(EventBadge badge) {
        if (badge != null){
            return badge.name;
        }
        return "없음";
    }
}
