package lib;
import java.util.Scanner;
///管理员操作选择面板 图书管理 订单管理 用户管理
public class SUMenu {
    public static void displayMenu(Scanner ioIn) {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n欢迎来到用户操作界面");
            System.out.println("请选择操作：");
            System.out.println("1. 进入书籍管理系统");
            System.out.println("2. 进入订单管理系统");
            System.out.println("3. 进入用户管理系统");
            System.out.println("4. 返回上级菜单"); // 假设的第四个选项
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine();

                if (choice == 1) {
                    System.out.println("正在进入书籍管理系统");
                    BooksFunction.displayMenu(ioIn);
                } else if (choice == 2) {
                    System.out.println("正在查看购物车。");
                    // TODO: 实现查看购物车界面的逻辑
                } else if (choice == 3) {
                    System.out.println("正在查看历史订单历史订单。");
                    // TODO: 实现查看历史订单界面的逻辑
                } else if (choice == 4) {
                    System.out.println("正在返回上级菜单。");
                    exit = true; // 返回主菜单
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字选项。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }
}
