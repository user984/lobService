package example.lob;


import java.util.List;

/**
 * Copyright 2017 (C) Java LOB
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
public interface LiveOrderBoard
{
    List<Order> getOrders();

    List<Order> addOrder(Order order);

    List<Order> deleteOrder(int orderId);

}
