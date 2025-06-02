package lib;

import com.sun.org.apache.xpath.internal.objects.XString;
import data.Account;
import data.AccountStorage;

/// 实现用户注册
public class AccountSignUp {
    /// 实现注册账号的功能
    public static boolean SignUp(String userName, String password){
        if(AccountStorage.accounts.containsKey(userName)){/// 在hashmap中查找是否有对应的键值对
            return false; /// 账号已存在，注册失败
        }
        else {
            Account newAccount = new Account(userName, password);
            AccountStorage.accounts.put(userName, newAccount);
            return true; /// 账号注册成功
        }
    }
}
