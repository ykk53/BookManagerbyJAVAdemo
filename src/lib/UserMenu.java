package lib;

import java.util.Scanner;

public class UserMenu {
    public static void displayMenu(Scanner ioIn) {
        int choice;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n欢迎来到用户操作界面");
            System.out.println("请选择操作：");
            System.out.println("1. 查看书库");
            System.out.println("2. 查看购物车");
            System.out.println("3. 查看历史订单");
            System.out.println("4. 返回上级菜单"); // 假设的第四个选项
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine();

                if (choice == 1) {
                    System.out.println("正在进入藏书库。");
                    // TODO: 实现查看书库界面的逻辑
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
