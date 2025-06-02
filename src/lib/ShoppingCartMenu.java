package lib;

import data.Account;
import data.Book;

import java.util.List;
import java.util.Scanner;
/// 购物车展示页面
public class ShoppingCartMenu {
    public static void display(Scanner ioIn, Account loggedInAccount){
        int choice;
        boolean exit = false;
        double TotalPrice = 0;
        while (!exit) {
            System.out.println("\n--- 您的购物车 ---");
            List<Book> cart = loggedInAccount.getShoppingCart();
            if (cart.isEmpty()) {
                System.out.println("您的购物车是空的。");
            } else {
                for (int i = 0; i < cart.size(); i++) {
                    Book book = cart.get(i);
                    System.out.println((i + 1) + ". 书名: " + book.getName() + ", 价格: " + book.getPrice() + ", 类型: " + book.getType());
                    TotalPrice += book.getPrice();
                }
                System.out.println("-");
                System.out.println("商品总价: " + String.format("%.2f", TotalPrice) + " 元");
            }

            System.out.println("\n请选择操作：");
            System.out.println("1. 移除商品");
            System.out.println("2. 前往结算");
            System.out.println("0. 返回用户菜单");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine(); // Consume newline
                /// 删除购物车内的图书
                if (choice == 1) {
                    System.out.println("正在进入购物车图书删除界面");
                    RemoveShoppingCartBook.removeItemFromCart(ioIn, loggedInAccount);
                }else if (choice == 2) {
                    CheckoutShoppingCart.checkout(ioIn, loggedInAccount);
                    exit = true;
                    break;
                } else if (choice == 0) {
                    System.out.println("返回用户菜单。");
                    exit = true;
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字选项。");
                ioIn.nextLine(); // Consume invalid input
            }
        }
    }
}
