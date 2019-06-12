package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.model.Order;
import com.springboot.ecommerce.springbootecommerce.model.OrderForm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderService {

    @NotNull List<Order> getAllOrders();
    Order getOrder(Long orderId);
    void createOrder(@NotNull @Valid OrderForm orderForm);
    void cancelOrder(@NotNull Long orderId);
    void deleteOrder(Long orderId);
    Double getOrderValue(Long orderId);
}
