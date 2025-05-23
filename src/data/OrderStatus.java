package data;

public enum OrderStatus {
    PLACED_AWAITING_SHIPMENT("已下单待发货"),
    SHIPPED("已发货"),
    DELIVERED("已送达");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}