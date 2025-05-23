package lib;

import data.Account;
import data.Book;
import data.MembershipLevel;
import java.util.List;
import java.util.Scanner;

public class CheckoutShoppingCart {

    public static void checkout(Scanner ioIn, Account loggedInAccount) {
        List<Book> cart = loggedInAccount.getShoppingCart();

        if (cart.isEmpty()) {
            System.out.println("您的购物车是空的，无法结算。");
            return;
        }

        System.out.println("\n--- 结算清单 ---");
        double originalTotalPrice = 0;
        for (Book book : cart) {
            System.out.println("书名: " + book.getName() + ", 价格: " + book.getPrice());
            originalTotalPrice += book.getPrice();
        }
        System.out.println("--------------------");
        System.out.println("商品总价: " + String.format("%.2f", originalTotalPrice) + " 元");

        // 2. 计算折后价
        double discountedPrice = calculateDiscountedPrice(originalTotalPrice, loggedInAccount.getMembershipLevel());
        System.out.println("会员折扣后价格: " + String.format("%.2f", discountedPrice) + " 元");

        // 3. 考虑运费券 (稍后实现)

        // TODO: 添加确认订单和支付的逻辑
    }

    // 计算折扣价格的方法
    private static double calculateDiscountedPrice(double originalPrice, MembershipLevel membershipLevel) {
        double discountRate = 0;
        switch (membershipLevel) {
            case NORMAL:
                discountRate = 0.0; // 普通会员无折扣
                break;
            case SILVER:
                discountRate = 0.05; // 白银会员 5% 折扣
                break;
            case GOLD:
                discountRate = 0.10; // 黄金会员 10% 折扣
                break;
            case DIAMOND:
                discountRate = 0.15; // 钻石会员 15% 折扣
                break;
        }
        return originalPrice * (1 - discountRate);
    }
}
