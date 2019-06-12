package com.springboot.ecommerce.springbootecommerce.controller;

import com.springboot.ecommerce.springbootecommerce.model.Order;
import com.springboot.ecommerce.springbootecommerce.model.OrderForm;
import com.springboot.ecommerce.springbootecommerce.service.OrderService;
import com.springboot.ecommerce.springbootecommerce.utility.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(Mappings.API_ORDERS)
public class OrderController {

    @Autowired
    private OrderService orderService;

    //http://localhost:8080/api/orders
    @GetMapping(value = {"/",""})
    public Iterable<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    //http://localhost:8080/api/orders/{id}
    @GetMapping(Mappings.ORDER_ID)
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    //http://localhost:8080/api/orders/neworder
    @PostMapping(Mappings.CREATE_ORDER)
    public void createNewOrder(@NotNull @RequestBody OrderForm orderForm){
        orderService.createOrder(orderForm);
    }

    //http://localhost:8080/api/orders/{id}/cancel
    @PutMapping(Mappings.CANCEL_ORDER)
    public void cancelOrderById(@PathVariable Long id){
        orderService.cancelOrder(id);
    }

    //http://localhost:8080/api/orders/{id}/delete
    @DeleteMapping(Mappings.DELETE_ORDER)
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteOrder(id);
    }

    //http://localhost:8080/api/orders/{id}/status
    @GetMapping(Mappings.ORDER_STATUS)
    public String getOrderStatus(@PathVariable Long id){
        Order order = orderService.getOrder(id);
        return order.getStatus();
    }

    //http://localhost:8080/api/orders/{id}/value
    @GetMapping(Mappings.ORDER_VALUE)
    public Double getOrderValue(@PathVariable Long id){
        return orderService.getOrderValue(id);
    }

}
