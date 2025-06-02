package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStorage {
    public static Map<String, Order> orders = new HashMap<>();
    public static List<String> orderIdList = new ArrayList<>();
    private static int nextOrderId = 1;

    public static String getNextOrderId() {
        return String.valueOf(nextOrderId++);
    }
}