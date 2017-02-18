package example.lob;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.*;

/**
 * Copyright 2017 (C) Java LOB
 *
 * Created on : 15/02/17
 * Author     : vicky
 */
public class LobServiceImpl implements LobService
{
    private LiveOrderBoard lob =  new LiveOrderBoardImpl("initialOrders.txt");

    public void setLob(LiveOrderBoard lob) {
        this.lob = lob;
    }

    private static Logger logger = Logger.getLogger(LobServiceImpl.class);


    @Override
    public List<Order> getLiveOrderSummary() {
        if (lob.getOrders().size() == 0) {
            logger.info(" No orders in Live Order Board");
        } else {
            logger.info(" Printing summary of Live Order Board ");

            printOrderSummary(lob.getOrders());
        }
        return lob.getOrders();
    }

    private void printOrderSummary(List<Order> orders) {
        logger.info("Printing BUY orders");
        List<Order> buyList = orders.stream().filter(order -> order.getOrderType().equals("BUY")).collect(toList());
        if (buyList.size() == 0) {
            logger.info(" *** No BUY orders ***");
        } else {
            Map<Integer, Double> unsortedMap = buyList.stream().collect(groupingBy(Order::getPrice,
                    summingDouble((o)-> o.getOrderQuantity().doubleValue())));
            TreeMap<Integer, Double> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(unsortedMap);
            sortedMap.forEach((price, qty) -> logger.info(qty + " kg for £" + price));
        }
        logger.info("Printing SELL orders");
        List<Order> sellList = orders.stream().filter(order -> order.getOrderType().equals("SELL")).collect(toList());
        if (sellList.size() == 0) {
            logger.info(" *** No SELL orders ***");
        } else {
            Map<Integer, Double> unsortedMap = sellList.stream().collect(groupingBy(Order::getPrice,
                    summingDouble((o)-> o.getOrderQuantity().doubleValue())));
            TreeMap<Integer, Double> sortedMap = new TreeMap<>(unsortedMap);
            sortedMap.forEach((price, qty) -> logger.info(qty + " kg for £" + price));
        }
    }

    @Override
    public List<Order> registerNewOrder(Order order) {
        return lob.addOrder(order);
    }

    @Override
    public List<Order> cancelOrder(int orderId) {
        return lob.deleteOrder(orderId);
    }

}
