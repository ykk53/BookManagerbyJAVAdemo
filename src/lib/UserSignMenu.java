package lib;

import data.Account;
import data.AccountStorage;
import data.VIPLevel;

import java.util.Scanner;
/// 实现用户登入的面板
public class UserSignMenu {
    public static void displayMenu(Scanner ioIn) {
        int chiose;
        boolean exit = false;
        while (!exit) {
            /// 菜单栏
            System.out.println("\n欢迎来登录界面");
            System.out.println("请选择操作：");
            System.out.println("1. 输入用户名和密码进行登录");
            System.out.println("2. 退出");
            System.out.print("请输入您的选择：");
            if (ioIn.hasNextInt()) {
                chiose = ioIn.nextInt();
                ioIn.nextLine();
                if (chiose == 1) {
                    /// 输入账户密码
                    System.out.println("\n请输入用户名。");
                    String userName = ioIn.nextLine();
                    System.out.println("请输入密码。");
                    String password = ioIn.nextLine();

                    if(AccountStorage.accounts.containsKey(userName)){
                        ///获得当前用户名下的账户数据
                        Account account = AccountStorage.accounts.get(userName);
                        if (account.getPassword().equals(password))
                        {
                            if (account.getMembershipLevel() == VIPLevel.UNEXAMINED) {
                                System.out.println("\n您的账户尚未通过审核，请耐心等待。");
                                break;
                            }
                            else{
                            System.out.println("\n登录成功！欢迎您，" + account.getUserName() + "！");
                            /// 进入用户界面,并导入成功登入的用户
                            UserMenu.displayMenu(ioIn,account);
                            exit = true; /// 登录成功后退出登录界面
                            break;
                            }
                        }///判断密码是否正确
                        else{
                            System.out.println("\n密码错误，请重新尝试。");
                        }
                    }///判断用户是否存在
                    else{
                        System.out.println("\n用户名不存在，请检查您输入的用户名或先注册账号。");
                    }
                } else if (chiose == 2) {
                    /// 退出
                    System.out.println("\n已退出登录界面，正在返回上级界面");
                    exit = true;
                    break;
                } else {/// 无效输入
                    System.out.println("\n无效的选择，请重新输入。");
                }
            }
            else{/// 输入非int
                System.out.println("\n输入格式错误，请输入数字。");
                ioIn.nextLine(); ///清空缓冲区
            }
        }
    }
}