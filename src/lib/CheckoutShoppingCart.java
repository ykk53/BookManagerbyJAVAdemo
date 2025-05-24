package lib;

import data.Account;
import data.Book;
import data.VIPLevel;
import java.util.List;
import java.util.Scanner;

public class CheckoutShoppingCart {

    public static void checkout(Scanner ioIn, Account loggedInAccount) {
        double ShippingCoupons = 10;
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

        double discountedPrice = CheckoutShoppingCart.calculateDiscountedPrice(originalTotalPrice, loggedInAccount.getMembershipLevel());
        System.out.println("会员折扣后价格: " + String.format("%.2f", discountedPrice) + " 元");
        System.out.println("另外需邮费" + ShippingCoupons + " 元");

        System.out.print("\n是否要使用免邮费券？(y/n): ");///判断邮费券
        String expressFee = ioIn.nextLine();
        if (expressFee.equalsIgnoreCase("y")||expressFee.equalsIgnoreCase("Y")) {
            if(loggedInAccount.getFreeShippingCoupons() == 0){/// 计算邮费券消耗
                System.out.println("您的邮费券已用完，如需邮费券请升级用户会员等级");
            }
            else{
                loggedInAccount.setFreeShippingCoupons(loggedInAccount.getFreeShippingCoupons()-1);
                ShippingCoupons -= 10;
                System.out.print("\n已为你使用邮费券");
                System.out.println("当前所需邮费为" + ShippingCoupons + " 元");
            }
        } else {
            System.out.println("已取消结算。");
        }
        //TODO：支付价格（暂时不实现）
        System.out.print("\n确认要结算并生成订单吗？(y/n): ");///判断结算
        String confirmation = ioIn.nextLine();
        if (confirmation.equalsIgnoreCase("y")||confirmation.equalsIgnoreCase("Y")) {
            OrderFunction.createOrderFromCart(loggedInAccount, cart, discountedPrice+ShippingCoupons); // 调用 OrderFunction.checkout 生成订单
        } else {
            System.out.println("已取消结算。");
        }
    }

    // 计算折扣价格的方法
    private static double calculateDiscountedPrice(double originalPrice, VIPLevel VIPLevel) {
        double discountRate = 0;
        switch (VIPLevel) {
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
