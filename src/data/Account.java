package data;
import static data.MembershipLevel.NORMAL;///构造函数设定会员等级
/// 用户类
public class Account {
    /// 用户元素
    private String userName;
    private String password;
    private MembershipLevel membershipLevel;
    /// 历史订单数据和管理系统的接口

    public Account(String userName, String password){/// 构造函数进行用户初始化
        this.userName = userName;
        this.password = password;
        membershipLevel = NORMAL;
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

}
