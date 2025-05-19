package lib;
import java.util.Scanner;
/// 实现注册用户的窗口
public class UserRegisterMenu {
    public static void displayMenu(Scanner ioIn) {
        /// 初始化数据
        int chiose;
        boolean exit = false;
        while(!exit) {
            ///菜单栏
            System.out.println("\n欢迎来到用户注册界面");
            System.out.println("请选择操作：");
            System.out.println("1. 输入注册账号用户名及密码");
            System.out.println("2. 退出注册界面");
            System.out.print("请输入您的选择：");
            if(ioIn.hasNextInt()){
                chiose = ioIn.nextInt();
                ioIn.nextLine();///清空缓冲区
                if (chiose == 1)/// 进入用户登入界面
                {
                    boolean passwordCheck = false;
                    while(!passwordCheck){
                        System.out.println("\n请输入注册的账号的用户名。");
                        String userName = ioIn.nextLine();
                        System.out.println("请输入注册的账号的密码。");
                        String password1 = ioIn.nextLine();
                        System.out.println("请再次输入注册的账号的密码。");
                        String password2 = ioIn.nextLine();

                        if(password1.equals(password2)) {
                            passwordCheck = true;
                            System.out.println("\n开始注册账号");

                            if(AccountSignUp.SignUp(userName,password1)){
                                System.out.println("\n账号注册成功正在返回主界面");
                                exit = true;
                                break;
                            }///检测账号注册是否成功
                            else {
                                System.out.println("\n账号已存在请重新输入");
                            }///进行账号注册

                        }///判断两次密码输入是否一致
                        else {
                            passwordCheck = false;
                            System.out.println("\n两次密码输入不一致请重新注册");
                        }
                    }
                }
                else if (chiose == 2){

                    System.out.println("\n已退出注册页面,正在返回上级页面");
                    exit = true;
                    break;
                }/// 退出
                else {
                    System.out.println("\n无效的选择，请重新输入。");
                }/// 无效输入
            }
            else{
                System.out.println("\n输入格式错误，请输入数字。");
                ioIn.nextLine(); ///清空缓冲区
            }/// 输入非int
        }
    }
}
