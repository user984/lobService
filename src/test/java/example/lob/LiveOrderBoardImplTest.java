package example.lob;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Copyright 2017 (C) Java RFQ
 * <p>
 * Created on : 18/02/17
 * Author     : vicky
 */
public class LiveOrderBoardImplTest
{
    private LiveOrderBoardImpl lob;

    @Test
    public void addOrder() throws Exception {
        lob = new LiveOrderBoardImpl();
        DecimalFormat df = new DecimalFormat();
        df.setParseBigDecimal(true);
        lob.setOrderList(

                Lists.newArrayList(

                        new OrderBuilder()
                        .setOrderId(100)
                        .setOrderType("BUY")
                        .setUserId("user 1")
                        .setOrderQuantity((BigDecimal) df.parse("4.5"))
                        .setPrice(Integer.parseInt("309")).createOrder()
                ));
        lob.addOrder(new OrderBuilder()
                .setOrderId(200)
                .setOrderType("SELL")
                .setUserId("user 100")
                .setOrderQuantity((BigDecimal) df.parse("99.5"))
                .setPrice(Integer.parseInt("1009")).createOrder());
        assertEquals(2, lob.getOrderList().size());
        assertEquals(100, lob.getOrderList().get(0).getOrderId());
        assertEquals(200, lob.getOrderList().get(1).getOrderId());
    }

    @Test
    public void deleteOrder() throws Exception {
        lob = new LiveOrderBoardImpl();
        DecimalFormat df = new DecimalFormat();
        df.setParseBigDecimal(true);
        lob.setOrderList(

                Lists.newArrayList(

                new OrderBuilder()
                .setOrderId(100)
                .setOrderType("BUY")
                .setUserId("user 1")
                .setOrderQuantity((BigDecimal) df.parse("4.5"))
                .setPrice(Integer.parseInt("309")).createOrder()

                , new OrderBuilder()
                        .setOrderId(101)
                        .setOrderType("SELL")
                        .setUserId("user 2")
                        .setOrderQuantity((BigDecimal) df.parse("5.5"))
                        .setPrice(Integer.parseInt("308")).createOrder()
                ));
        lob.deleteOrder(101);
        assertEquals(1, lob.getOrderList().size());
        assertEquals(100, lob.getOrderList().get(0).getOrderId());
    }
}