package example.lob;

import java.util.List;

/**
 * Copyright 2017 (C) Java Live Order Board
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
public interface LobService
{
    List<Order> getLiveOrderSummary();

    List<Order> registerNewOrder(Order order);

    List<Order> cancelOrder(int orderId);

}
