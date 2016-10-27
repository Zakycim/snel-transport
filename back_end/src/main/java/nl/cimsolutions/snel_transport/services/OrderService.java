package nl.cimsolutions.snel_transport.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.cimsolutions.snel_transport.models.Order;



public class OrderService {

//    private Map<Long, Order> Orders = DatabaseClass.getOrders();
    
    public OrderService() {
//        Orders.put(1L, new Order(1, "Hello World", "koushik"));
//        Orders.put(2L, new Order(2, "Hello Jersey", "koushik"));
    }
    
    public Order addOrder(Order Order) {
//        Order.setId("1");
//        Orders.put(Order.getId(), Order);
        return Order;
    }
    
    public Order updateOrder(Order Order) {
        if (Order.getId() <= 0) {
            return null;
        }
//        Orders.put(Order.getId(), Order);
        return Order;
    }
    
//    public Order removeOrder(long id) {
////        return Orders.remove(id);
//    }

}
