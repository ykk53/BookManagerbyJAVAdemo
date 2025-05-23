package data;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.LocalDateTime;
import java.util.List;
/// 订单类
public class Order {
    private String orderId;
    private String userId;
    private LocalDateTime orderDate;
    private double totalPrice;
    private OrderStatus orderStatus;
    private List<Book> orderItems;

    public Order(String orderId, String userId, LocalDateTime orderDate, double totalPrice, OrderStatus orderStatus, List<Book> orderItems) {
        /// 订单号（按照加入顺序1234排序） 用户id 下单日期 总价 订单状态 订单内容
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Book> getOrderItems() {
        return orderItems;
    }

    // 可以根据需要添加 setter 方法或者其他操作方法
}
