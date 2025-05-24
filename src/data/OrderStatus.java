package data;

public enum OrderStatus {
    AWAITING_SHIPMENT("已下单待发货"),
    SHIPPED("已发货"),
    COMPLETED("订单已完成"),
    CANCELLED("已取消");

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