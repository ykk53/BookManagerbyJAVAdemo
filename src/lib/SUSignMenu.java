package lib;

import java.util.Scanner;
/// 管理员登入验证 静态存储了一个管理员账号
/// 账号： root 密码： admin
public class SUSignMenu {
    public static void displayMenu(Scanner ioIn) {
        int choice;
        boolean exit = false;
        while (!exit) {
            /// 菜单栏
            System.out.println("\n欢迎来到管理员登录界面");
            System.out.println("请选择操作：");
            System.out.println("1. 输入管理员用户名和密码进行登录");
            System.out.println("2. 退出");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine();
                if (choice == 1) {
                    System.out.println("\n请输入管理员用户名：");
                    String adminUserName = ioIn.nextLine();
                    System.out.println("请输入管理员密码：");
                    String adminPassword = ioIn.nextLine();

                    // 硬编码管理员账户验证
                    if (adminUserName.equals("root") && adminPassword.equals("admin")) {
                        System.out.println("\n管理员登录成功！");
                        SUMenu.displayMenu(ioIn);
                        exit = true; // 暂时登录成功后退出管理员登录界面
                        break;
                    } else {
                        System.out.println("\n管理员用户名或密码错误，请重新尝试。");
                    }
                } else if (choice == 2) {
                    /// 退出
                    System.out.println("已退出管理员登录界面，正在返回上级界面");
                    exit = true;
                    break;
                } else {/// 无效输入
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {/// 输入非int
                System.out.println("输入格式错误，请输入数字选项。");
                ioIn.nextLine();
            }
        }
    }
}
