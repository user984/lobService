package example.lob;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2017 (C) Java LiveOrder Board
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
public class LiveOrderBoardImpl implements LiveOrderBoard
{
    List<Order> getOrderList() {
        return orderList;
    }

    void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    private List<Order> orderList = Lists.newArrayList();

    private static Logger logger = Logger.getLogger(LiveOrderBoardImpl.class);

    LiveOrderBoardImpl(final String fileName) {
        logger.info("Populating Live Order Board from data source(text file).");
        List<String> orders;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            orders = Files.readLines(file, Charset.forName("utf-8"));
            populateOrderList(orders);
        } catch (IOException  | ParseException p) {
            logger.fatal("Unable to initialize Live Order Board" + p.getMessage());
        }
    }

    LiveOrderBoardImpl() {}

    private void populateOrderList(List<String> orders) throws ParseException {
        DecimalFormat df = new DecimalFormat();
        df.setParseBigDecimal(true);
        for (String order: orders) {
            List<String> orderDetail = Arrays.asList(order.split(","));
            orderList.add(new OrderBuilder().setOrderId(Integer.parseInt(orderDetail.get(0))).setOrderType(orderDetail.get(1)).setUserId(orderDetail.get(2))
                    .setOrderQuantity((BigDecimal) df.parse(orderDetail.get(3)))
                    .setPrice(Integer.parseInt(orderDetail.get(4))).createOrder());
        }
    }

    @Override
    public List<Order> getOrders() {
        return orderList;
    }

    @Override
    public List<Order> addOrder(Order order) {
         orderList.add(order);
         return orderList;
    }

    @Override
    public List<Order> deleteOrder(int orderId) {
        List<Order> filteredList = getOrderList().stream().filter(
                order -> order.getOrderId() != orderId).collect(Collectors.toList());
        if (filteredList.size() + 1 == getOrderList().size()) {
            setOrderList(filteredList);
        }
        return orderList;
    }
}

