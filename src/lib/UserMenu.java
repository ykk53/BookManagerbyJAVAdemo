package lib;

import data.Account;
import data.Book;

import java.util.Scanner;

/// 用户操作面版

public class UserMenu {
    public static void displayMenu(Scanner ioIn, Account loggedInAccount) {/// 传入ioin和已登录的账号
        int choice;
        boolean exit = false;
        while (!exit) {
            boolean ifExit = false;
            System.out.println("\n欢迎来到用户操作界面，" + loggedInAccount.getUserName() + "！");
            System.out.println("请选择操作：");
            System.out.println("1. 查看书库");
            System.out.println("2. 查看购物车");
            System.out.println("3. 查看历史订单");
            System.out.println("4. 升级会员等级");
            System.out.println("0. 返回上级菜单"); // 假设的第四个选项
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine();

                if (choice == 1) {
                    System.out.println("正在进入藏书库。");
                    while (ifExit == false) {
                        Book selectedBook =  BooksFunction.displayBook(ioIn);
                        if (selectedBook != null) {
                            loggedInAccount.addBookToCart(selectedBook);
                            System.out.println("书籍 '" + selectedBook.getName() + "' 已添加到您的购物车。");
                        }
                        System.out.println("是否继续选择书籍？(y/n)");
                        String input = ioIn.next();
                        if (input.equals("n")) {
                            ifExit = true;
                        } else if (input.equals("y")) {
                            ifExit = false;
                        } else {
                            System.out.println("无效的选择，请重新输入。");
                            ioIn.nextLine(); // 清空缓冲区
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("正在查看购物车。");
                    ShoppingCartMenu.display(ioIn,loggedInAccount);
                } else if (choice == 3) {
                    System.out.println("正在查看历史订单。");
                    OrderFunction.displayOrder(ioIn, loggedInAccount);
                } else if (choice == 4) {
                    System.out.println("正在进入会员等级升级界面。");
                    VIPLevelUpgradeMenu.display(ioIn, loggedInAccount);
                } else if (choice == 0) {
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
