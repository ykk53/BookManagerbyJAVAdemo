package lib;

import data.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static data.OrderStatus.*;


public class OrderFunction {

    public static void createOrderFromCart(Account loggedInAccount, List<Book> cart, double finalPrice) { // 接收 Account 和购物车列表作为参数
        if (cart.isEmpty()) {
            System.out.println("购物车为空，无法生成订单。");
            return;
        }

        // 生成订单 ID
        String orderId = OrderStorage.getNextOrderId();

        // 获取当前时间
        LocalDateTime orderDate = LocalDateTime.now();

        // 设置订单状态为未发货
        OrderStatus orderStatus = AWAITING_SHIPMENT;

        // 使用传入的购物车列表
        List<Book> orderItems = new ArrayList<>(cart);

        // 创建 Order 对象
        data.Order order = new data.Order(orderId, loggedInAccount.getUserName(), orderDate, finalPrice, orderStatus, orderItems);

        // 将 Order 对象存储到 OrderStorage 中
        OrderStorage.orders.put(orderId, order);

        // 将订单 ID 添加到用户的订单历史中
        loggedInAccount.addOrderToHistory(orderId);

        // 清空用户的购物车
        loggedInAccount.getShoppingCart().clear();

        System.out.println("\n订单已创建，订单号为：" + orderId);
        System.out.println("请支付：" + finalPrice);
        System.out.println("感谢您的购买！");

    }
    public static void displayOrder(Scanner ioIn, Account loggedInAccount) {
        List<String> orderIds = loggedInAccount.getOrderHistory();
        int pageSize = 7; // 设置每页显示的订单数量
        int pageNow = 0;
        if (orderIds.isEmpty()) {
            System.out.println("\n您还没有任何订单记录。");
            return;
        }
        int totalPages = (int) Math.ceil((double) orderIds.size() / pageSize);

        while (true) {
            System.out.println("\n--- 您的订单历史 (第 " + (pageNow + 1) + " 页，共 " + totalPages + " 页) ---");

            int startIndex = pageNow * pageSize;
            int endIndex = Math.min(startIndex + pageSize, orderIds.size());

            for (int i = startIndex; i < endIndex; i++) {
                String orderId = orderIds.get(i);
                Order order = OrderStorage.orders.get(orderId);
                if (order != null) {
                    System.out.println((i - startIndex + 1) + ". 订单号: " + order.getOrderId() + ", 下单日期: " + order.getOrderDate().toLocalDate() + "\n总价: " + String.format("%.2f", order.getTotalPrice()) + ", 状态: " + order.getOrderStatus());
                } else {
                    System.out.println((i - startIndex + 1) + ". 订单号: " + orderId + " (订单信息不存在)");
                }
            }

            System.out.println("\n请选择操作：");
            System.out.println("输入 8 查看下一页");
            System.out.println("输入 9 查看上一页");
            System.out.println("输入 0 返回用户操作界面");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                int choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice == 8) {
                    // 查看下一页
                    if (pageNow < totalPages - 1) {
                        pageNow++;
                    } else {
                        System.out.println("已是最后一页。");
                    }
                } else if (choice == 9) {
                    // 查看上一页
                    if (pageNow > 0) {
                        pageNow--;
                    } else {
                        System.out.println("已是第一页。");
                    }
                } else if (choice == 0) {
                    // 退出
                    System.out.println("返回用户操作界面。");
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }///用户查看订单
    public static Order displayOrders(Scanner ioIn) {
        int pageSize = 7;
        int pageNow = 0;
        List<String> allOrderIds = OrderStorage.orderIdList; // 获取按时间排序的订单 ID 列表

        if (allOrderIds.isEmpty()) {
            System.out.println("\n暂无订单记录。");
            return null;
        }

        int totalPages = (int) Math.ceil((double) allOrderIds.size() / pageSize);

        while (true) {
            System.out.println("\n--- 所有订单列表 (按时间排序) (第 " + (pageNow + 1) + " 页，共 " + totalPages + " 页) ---");

            int startIndex = pageNow * pageSize;
            int endIndex = Math.min(startIndex + pageSize, allOrderIds.size());

            for (int i = startIndex; i < endIndex; i++) {
                String orderId = allOrderIds.get(i);
                Order order = OrderStorage.orders.get(orderId);
                if (order != null) {
                    System.out.println((i - startIndex + 1) + ". 订单号: " + order.getOrderId() + ", 用户: " + order.getUserId() + ", 下单日期: " + order.getOrderDate().toLocalDate() + ", 总价: " + String.format("%.2f", order.getTotalPrice()) + ", 状态: " + order.getOrderStatus());
                } else {
                    System.out.println((i - startIndex + 1) + ". 订单号: " + orderId + " (订单信息不存在)");
                }
            }

            System.out.println("\n请选择操作：");
            System.out.println("输入 1-" + Math.min(pageSize, allOrderIds.size() - startIndex) + " 选择订单");
            System.out.println("输入 8 查看下一页");
            System.out.println("输入 9 查看上一页");
            System.out.println("输入 0 返回管理员菜单");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                int choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice >= 1 && choice <= Math.min(pageSize, allOrderIds.size() - startIndex)) {
                    String selectedOrderId = allOrderIds.get(startIndex + choice - 1);
                    return OrderStorage.orders.get(selectedOrderId); // 返回选中的订单对象
                } else if (choice == 8) {
                    if (pageNow < totalPages - 1) {
                        pageNow++;
                    } else {
                        System.out.println("已是最后一页。");
                    }
                } else if (choice == 9) {
                    if (pageNow > 0) {
                        pageNow--;
                    } else {
                        System.out.println("已是第一页。");
                    }
                } else if (choice == 0) {
                    System.out.println("返回管理员菜单。");
                    return null;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine();
            }
        }
    }///管理员查看订单
    public static void bookList(Scanner ioIn, Order selectedOrder) {
        System.out.println("\n--- 订单号：" + selectedOrder.getOrderId() + " 包含的书籍 ---");
        List<Book> orderedBooks = selectedOrder.getOrderItems();
        if (orderedBooks.isEmpty()) {
            System.out.println("该订单中没有包含任何书籍。");
        } else {
            for (int i = 0; i < orderedBooks.size(); i++) {
                Book book = orderedBooks.get(i);
                System.out.println((i + 1) + ". 书名: " + book.getName() + ", 价格: " + book.getPrice() + ", 类型: " + book.getType());
            }
        }
        System.out.println("\n输入 0 返回上一级菜单");
        while (ioIn.hasNextInt()) {
            if (ioIn.nextInt() == 0) {
                ioIn.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("输入无效，请输入 0 返回。");
                ioIn.nextLine(); // Consume invalid input
            }
        }
    }///查看订单中的书籍
    public static void orderOperation(Scanner ioIn, Order selectedOrder) {
        int choice;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- 更改订单状态 ---");
            System.out.println("当前订单状态为：" + selectedOrder.getOrderStatus());
            System.out.println("请选择操作：");
            System.out.println("1. 更新为已下单未发货");
            System.out.println("2. 更新为已发货");
            System.out.println("3. 更新为订单已完成");
            System.out.println("4. 更新为订单已取消");
            System.out.println("5. 查看订单内书籍");
            System.out.println("0. 返回订单查看界面");
            System.out.print("请输入您的选择：");

            if (ioIn.hasNextInt()) {
                choice = ioIn.nextInt();
                ioIn.nextLine(); // 读取换行符

                if (choice == 1) {
                    selectedOrder.setOrderStatus(AWAITING_SHIPMENT);
                } else if (choice == 2) {
                    selectedOrder.setOrderStatus(SHIPPED);
                } else if (choice == 3) {
                    selectedOrder.setOrderStatus(COMPLETED);
                }else if (choice == 4) {
                    selectedOrder.setOrderStatus(CANCELLED);
                }else if(choice == 5) {
                    OrderFunction.bookList(ioIn ,selectedOrder);
                }else if (choice == 0) {
                    System.out.println("返回订单查看界面。");
                    exit = true;
                    break;
                } else {
                    System.out.println("无效的选择，请重新输入。");
                }
            } else {
                System.out.println("输入格式错误，请输入数字。");
                ioIn.nextLine(); // 清空缓冲区
            }
        }
    }///改变订单状态
}
