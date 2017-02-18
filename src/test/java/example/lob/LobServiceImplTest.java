package example.lob;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Copyright 2017 (C) Java LOB
 * <p>
 * Created on : 16/02/17
 * Author     : vicky
 */
@RunWith(MockitoJUnitRunner.class)
public class LobServiceImplTest
{
    @InjectMocks
    private LobServiceImpl lobService;

    @Mock
    private LiveOrderBoardImpl lob;

    @Test
    public void getLiveOrderSummary() throws Exception {
        lobService = new LobServiceImpl();
        lobService.setLob(lob);
        List<Order> orders = lobService.getLiveOrderSummary();
        assertEquals(8, orders.size());
        assertEquals(4, orders.stream().filter((order -> order.getOrderType().equals("BUY"))).collect(Collectors.toList()).size());
        assertEquals(4, orders.stream().filter((order -> order.getOrderType().equals("SELL"))).collect(Collectors.toList()).size());
    }

    @Test
    public void registerNewOrder() throws Exception {
        lobService = new LobServiceImpl();
        lobService.setLob(lob);
        assertEquals(1, lobService.registerNewOrder(new Order("BUY", "user 10", BigDecimal.valueOf(9.5), 900, 9)).size());
        verify(lob, times(1)).addOrder(any(Order.class));
    }

    @Test
    public void cancelOrder() throws Exception {
        lobService = new LobServiceImpl();
        lobService.setLob(lob);
        assertEquals(0, lobService.cancelOrder(1).size());
        verify(lob, times(1)).deleteOrder(1);

    }

    @Before
    public void setup() {
        //  create mock
        Order mockOrder1 = Mockito.mock(Order.class);
        Order mockOrder2 = Mockito.mock(Order.class);
        Order mockOrder3 = Mockito.mock(Order.class);
        Order mockOrder4 = Mockito.mock(Order.class);
        Order mockOrder5 = Mockito.mock(Order.class);
        Order mockOrder6 = Mockito.mock(Order.class);
        Order mockOrder7 = Mockito.mock(Order.class);
        Order mockOrder8 = Mockito.mock(Order.class);

        // Buy
        when(mockOrder1.getOrderId()).thenReturn(1);
        when(mockOrder1.getOrderQuantity()).thenReturn(BigDecimal.valueOf(20.0d));
        when(mockOrder1.getOrderType()).thenReturn("BUY");
        when(mockOrder1.getPrice()).thenReturn(801);
        when(mockOrder1.getUserId()).thenReturn("user 1");

        when(mockOrder2.getOrderId()).thenReturn(2);
        when(mockOrder2.getOrderQuantity()).thenReturn(BigDecimal.valueOf(19.0d));
        when(mockOrder2.getOrderType()).thenReturn("BUY");
        when(mockOrder2.getPrice()).thenReturn(802);
        when(mockOrder2.getUserId()).thenReturn("user 2");

        when(mockOrder3.getOrderId()).thenReturn(3);
        when(mockOrder3.getOrderQuantity()).thenReturn(BigDecimal.valueOf(20.0d));
        when(mockOrder3.getOrderType()).thenReturn("BUY");
        when(mockOrder3.getPrice()).thenReturn(801);
        when(mockOrder3.getUserId()).thenReturn("user 3");

        when(mockOrder4.getOrderId()).thenReturn(4);
        when(mockOrder4.getOrderQuantity()).thenReturn(BigDecimal.valueOf(20.0d));
        when(mockOrder4.getOrderType()).thenReturn("BUY");
        when(mockOrder4.getPrice()).thenReturn(803);
        when(mockOrder4.getUserId()).thenReturn("user 4");

        // Sell
        when(mockOrder5.getOrderId()).thenReturn(5);
        when(mockOrder5.getOrderQuantity()).thenReturn(BigDecimal.valueOf(30.0d));
        when(mockOrder5.getOrderType()).thenReturn("SELL");
        when(mockOrder5.getPrice()).thenReturn(700);
        when(mockOrder5.getUserId()).thenReturn("user 1");

        when(mockOrder6.getOrderId()).thenReturn(6);
        when(mockOrder6.getOrderQuantity()).thenReturn(BigDecimal.valueOf(31.0d));
        when(mockOrder6.getOrderType()).thenReturn("SELL");
        when(mockOrder6.getPrice()).thenReturn(701);
        when(mockOrder6.getUserId()).thenReturn("user 2");

        when(mockOrder7.getOrderId()).thenReturn(7);
        when(mockOrder7.getOrderQuantity()).thenReturn(BigDecimal.valueOf(32.0d));
        when(mockOrder7.getOrderType()).thenReturn("SELL");
        when(mockOrder7.getPrice()).thenReturn(702);
        when(mockOrder7.getUserId()).thenReturn("user 3");

        when(mockOrder8.getOrderId()).thenReturn(8);
        when(mockOrder8.getOrderQuantity()).thenReturn(BigDecimal.valueOf(31.5d));
        when(mockOrder8.getOrderType()).thenReturn("SELL");
        when(mockOrder8.getPrice()).thenReturn(701);
        when(mockOrder8.getUserId()).thenReturn("user 4");

        List<Order> mockOrders = Arrays.asList(mockOrder1, mockOrder2, mockOrder3, mockOrder4
                , mockOrder5, mockOrder6, mockOrder7, mockOrder8);
        when(lob.getOrders()).thenReturn(mockOrders);
        when(lob.deleteOrder(1)).thenReturn(Lists.newArrayList());
        when(lob.addOrder(any(Order.class))).thenReturn(Lists.newArrayList(mockOrder1));
    }
}