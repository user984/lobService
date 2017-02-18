package example.lob;

import java.math.BigDecimal;

/**
 * Copyright 2017 (C) Java LOB
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
class OrderBuilder {
    private String orderType;
    private String userId;
    private BigDecimal orderQuantity;
    private int price;
    private int orderId;

    public OrderBuilder setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public OrderBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public OrderBuilder setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }

    public OrderBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public OrderBuilder setOrderId(int orderId) {
        this.orderId = orderId;
        return this;
    }

    public Order createOrder() {
        return new Order(orderType, userId, orderQuantity, price, orderId);
    }
}