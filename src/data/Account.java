package data;
import java.util.ArrayList;
import java.util.List;

import static data.VIPLevel.*;

/// 用户类
public class Account {
    /// 用户元素
    private String userName;
    private String password;
    private VIPLevel VIPLevel;
    private List<Book> shoppingCart;
    private List<String> orderHistory;
    private int freeShippingCoupons;

    /// 历史订单数据和管理系统的接口

    public Account(String userName, String password){/// 构造函数进行用户初始化
        this.userName = userName;
        this.password = password;
        this.VIPLevel = NORMAL;
        this.shoppingCart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        setFreeShippingCoupons();
    }
    /// 常规的get和set的接口
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public VIPLevel getMembershipLevel() {
        return VIPLevel;
    }

    public void setMembershipLevel(VIPLevel VIPLevel) {
        this.VIPLevel = VIPLevel;
    }

    public int getFreeShippingCoupons() {
        return freeShippingCoupons;
    }

    /// 邮费券设置
    public void setFreeShippingCoupons() {
        switch (VIPLevel) {
            case NORMAL:
                this.freeShippingCoupons = 0; // 普通会员无折扣
                break;
            case SILVER:
                this.freeShippingCoupons = 0; // 白银会员 5% 折扣
                break;
            case GOLD:
                this.freeShippingCoupons = 1; // 黄金会员 10% 折扣
                break;
            case DIAMOND:
                this.freeShippingCoupons = 3; // 钻石会员 15% 折扣
                break;
        }
    }
    public void setFreeShippingCoupons(int freeShippingCoupons){
        this.freeShippingCoupons =  freeShippingCoupons;
    }
    /// 会员等级
    public void setVIPLevel(VIPLevel VIPLevel) {
        this.VIPLevel = VIPLevel;
    }
    public VIPLevel getVIPLevel() {
        return VIPLevel;
    }
    public static double getVIPPrice(VIPLevel level){
        double p = 0.0;
        switch (level) {
            case NORMAL:
                p = 0.0;
                break;
            case SILVER:
                p = 199;
                break;
            case GOLD:
                p = 399;
                break;
            case DIAMOND:
                p = 799;
                break;
        }
        return p;
    }
    public double getVIPPrice(){
        double p = 0.0;
        switch (this.VIPLevel) {
            case NORMAL:
                p = 0.0;
                break;
            case SILVER:
                p = 199;
                break;
            case GOLD:
                p = 399;
                break;
            case DIAMOND:
                p = 799;
                break;
        }
        return p;
    }
    public static String getLevelDisplayName(VIPLevel level) {
        switch (level) {
            case NORMAL:
                return "普通会员";
            case SILVER:
                return "白银会员";
            case GOLD:
                return "黄金会员";
            case DIAMOND:
                return "钻石会员";
            default:
                return "未知会员";
        }
    }

    /// 购物车管理
    public void addBookToCart(Book book) {
        this.shoppingCart.add(book);
    }//增加购物车内书籍
    public List<Book> getShoppingCart(){
        return this.shoppingCart;
    }//返回购物车列表

    ///订单管理
    public List<String> getOrderHistory() {
        return orderHistory;
    }
    public void addOrderToHistory(String orderId) {
        this.orderHistory.add(orderId);
    }
}
