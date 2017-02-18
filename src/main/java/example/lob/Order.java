package example.lob;

import java.math.BigDecimal;

/**
 * Copyright 2017 (C) Java LOB
 *
 * Created on : 14/02/17
 * Author     : vicky
 */
class Order
{
    public Order(String orderType, String userId, BigDecimal orderQuantity, int price, int orderId) {
        this.orderType = orderType;
        this.userId = userId;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String orderType;

    private String userId;

    private BigDecimal orderQuantity;

    private int price;

    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (price != order.price) return false;
        if (orderId != order.orderId) return false;
        if (!orderType.equals(order.orderType)) return false;
        return userId.equals(order.userId) && orderQuantity.equals(order.orderQuantity);
    }

    @Override
    public int hashCode() {
        int result = orderType.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + orderQuantity.hashCode();
        result = 31 * result + price;
        result = 31 * result + orderId;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderType='" + orderType + '\'' +
                ", userId='" + userId + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", price=" + price +
                ", orderId=" + orderId +
                '}';
    }
}
