package data;
import java.util.ArrayList;
import java.util.List;

import static data.MembershipLevel.NORMAL;///构造函数设定会员等级
/// 用户类
public class Account {
    /// 用户元素
    private String userName;
    private String password;
    private MembershipLevel membershipLevel;
    private List<Book> shoppingCart;
    private List<String> orderHistory;

    /// 历史订单数据和管理系统的接口

    public Account(String userName, String password){/// 构造函数进行用户初始化
        this.userName = userName;
        this.password = password;
        this.membershipLevel = NORMAL;
        this.shoppingCart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
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

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
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
