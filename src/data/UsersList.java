package data;
import java.util.ArrayList;
import java.util.List;
/// 程序内静态存储的用户数据
public class UsersList {
    public static List<Account> initialUsers = new ArrayList<>();

    static {
        initialUsers.add(new Account("testuser1", "password"));
        initialUsers.add(new Account("test", "pass"));
    }

    public static List<Account> getInitialUsers() {
        return initialUsers;
    }
}
