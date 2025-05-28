package lib;

import java.util.Scanner;
/// 主菜单  登入 注册 退出
public class MainMenu {
    public void displayMenu() {
        Scanner ioIn = new Scanner(System.in);
        int chiose;
        boolean exit = false;
        while(!exit){
            /// 菜单栏
            System.out.println("\n欢迎来到在线书店管理系统");
            System.out.println("请选择操作：");
            System.out.println("1. 登入");
            System.out.println("2. 注册");
            System.out.println("3. 登录管理员账号");
            System.out.println("4. 退出");
            System.out.print("请输入您的选择：");

            if(ioIn.hasNextInt()){
                chiose = ioIn.nextInt();
                ioIn.nextLine();

                if (chiose == 1){
                    /// 进入用户登入界面
                    System.out.println("\n正在进入用户登录界面");
                    UserSignMenu.displayMenu(ioIn);
                }
                else if (chiose == 2){
                    /// 进入注册页面
                    System.out.println("\n正在进入用户注册界面");
                    UserRegisterMenu.displayMenu(ioIn);
                }
                else if (chiose == 3){
                    /// 进入管理员账号登入
                    System.out.println("\n正在进入管理员用户登录界面");
                    SUSignMenu.displayMenu(ioIn);
                }
                else if (chiose == 4){
                    /// 退出
                    System.out.println("\n谢谢使用，再见！");
                    exit = true;
                    break;
                }
                else {/// 无效输入
                    System.out.println("\n无效的选择，请重新输入。");
                }
            }
            else{/// 输入非int
                System.out.println("\n输入格式错误，请输入数字。");
                ioIn.nextLine(); ///清空缓冲区
            }
        }
        ioIn.close();
    }
}