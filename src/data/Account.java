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

    /// 购物车管理


    /// 历史订单数据和管理系统的接口



    public Account(String userName, String password){/// 构造函数进行用户初始化
        this.userName = userName;
        this.password = password;
        this.membershipLevel = NORMAL;
        this.shoppingCart = new ArrayList<>();
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
    public void addBookToCart(Book book) {
        this.shoppingCart.add(book);
    }
}
