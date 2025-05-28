package lib;

import data.Account;
import data.Book;
import java.util.List;
import java.util.Scanner;

public class RemoveShoppingCartBook {

    public static void removeItemFromCart(Scanner ioIn, Account loggedInAccount) {
        List<Book> cart = loggedInAccount.getShoppingCart();
        if (cart.isEmpty()) {
            System.out.println("您的购物车是空的，没有商品可以移除。");
            return;
        }

        boolean removing = true;
        while (removing) {
            System.out.println("\n--- 您的购物车 ---");
            for (int i = 0; i < cart.size(); i++) {
                Book book = cart.get(i);
                System.out.println((i + 1) + ". 书名: " + book.getName() + ", 价格: " + book.getPrice() + ", 类型: " + book.getType());
            }
            System.out.print("\n请输入要移除的商品编号（输入 0 完成移除）：");
            if (ioIn.hasNextInt()) {
                int itemNumberToRemove = ioIn.nextInt();
                ioIn.nextLine(); // Consume newline
                if (itemNumberToRemove >= 1 && itemNumberToRemove <= cart.size()) {
                    Book removedBook = cart.remove(itemNumberToRemove - 1); // 移除索引对应的商品
                    System.out.println("已将 '" + removedBook.getName() + "' 从购物车中移除。");
                } else if (itemNumberToRemove == 0) {
                    System.out.println("完成移除，返回购物车菜单。");
                    removing = false; // 设置为 false 退出循环
                } else {
                    System.out.println("无效的商品编号，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字编号。");
                ioIn.nextLine(); // Consume invalid input
            }
            if (cart.isEmpty() && removing) { // 如果在移除过程中购物车变空，也退出循环
                System.out.println("购物车已清空。");
                removing = false;
            }
        }
    }
}