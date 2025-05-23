package data;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
    public static List<Account> initialUsers = new ArrayList<>();

    static {
        initialUsers.add(new Account("testuser1", "password"));
        initialUsers.add(new Account("testuser2", "secure"));
    }

    public static List<Account> getInitialUsers() {
        return initialUsers;
    }
}
