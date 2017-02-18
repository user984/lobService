package example.lob;

import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Copyright 2017 (C) Java LOB
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
public class LobMain
{
    private static Logger logger = Logger.getLogger(LobMain.class);

    public static void main(String[] args) {
        logger.info("Starting Live Order Board service");
        LobMain lobMain = new LobMain();
        lobMain.runProgram();
        logger.info("Finishing...");
    }

    private void runProgram() {
        LobService lobService = new LobServiceImpl();
        lobService.getLiveOrderSummary();
        logger.info("Cancelling orders.");
        lobService.cancelOrder(1);
        logger.info("Printing summary.");
        lobService.getLiveOrderSummary();
        logger.info("Adding a new order");
        lobService.registerNewOrder(new OrderBuilder()
                .setOrderType("BUY")
                .setUserId("user 1")
                .setOrderQuantity(BigDecimal.valueOf(9.9))
                .setPrice(501)
                .setOrderId(9).createOrder());
        logger.info("Printing summary.");
        lobService.getLiveOrderSummary();
    }

}

