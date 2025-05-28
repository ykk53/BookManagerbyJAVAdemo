package data;
/// 用户等级
public enum VIPLevel {
    NORMAL("普通会员"),
    SILVER("白银会员"),
    GOLD("黄金会员"),
    DIAMOND("钻石会员");

    private final String displayName;

    VIPLevel(String displayName) {
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
