package lib;

import data.Account;
import data.Book;
import data.OrderStatus;
import data.OrderStorage;
import data.MembershipLevel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderFunction {

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

        double discountedPrice = calculateDiscountedPrice(originalTotalPrice, loggedInAccount.getMembershipLevel());
        System.out.println("会员折扣后价格: " + String.format("%.2f", discountedPrice) + " 元");

        // 生成订单 ID
        String orderId = OrderStorage.getNextOrderId();

        // 获取当前时间
        LocalDateTime orderDate = LocalDateTime.now();

        // 设置订单状态为未发货
        OrderStatus orderStatus = OrderStatus.PLACED_AWAITING_SHIPMENT;

        // 从购物车中获取书籍列表（创建新的列表以避免引用问题）
        List<Book> orderItems = new ArrayList<>(cart);

        // 计算最终订单总价 (这里我们使用折扣后的价格)
        double finalPrice = discountedPrice; // 可以根据需要添加运费等

        // 创建 Order 对象
        data.Order order = new data.Order(orderId, loggedInAccount.getUserName(), orderDate, finalPrice, orderStatus, orderItems);

        // 将 Order 对象存储到 OrderStorage 中
        OrderStorage.orders.put(orderId, order);

        // 将订单 ID 添加到用户的订单历史中
        loggedInAccount.addOrderToHistory(orderId);

        // 清空用户的购物车
        cart.clear();

        System.out.println("\n订单已创建，订单号为：" + orderId);
        System.out.println("感谢您的购买！");

        // TODO: 可以添加后续的支付等逻辑
    }

    private static double calculateDiscountedPrice(double originalPrice, MembershipLevel membershipLevel) {
        double discountRate = 0;
        switch (membershipLevel) {
            case NORMAL:
                discountRate = 0.0;
                break;
            case SILVER:
                discountRate = 0.05;
                break;
            case GOLD:
                discountRate = 0.10;
                break;
            case DIAMOND:
                discountRate = 0.15;
                break;
        }
        return originalPrice * (1 - discountRate);
    }
}