package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.exception.OrderNotFoundException;
import com.springboot.ecommerce.springbootecommerce.model.*;
import com.springboot.ecommerce.springbootecommerce.repository.OrderRepository;
import com.springboot.ecommerce.springbootecommerce.utility.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    @Override
    public @NotNull List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No order found with id "+ orderId));
    }

    @Override
    public void createOrder(@NotNull @Valid OrderForm orderForm) {
        Order order = new Order();
        ListIterator<OrderItem> orderListIterator = orderForm.getOrderList().listIterator();
        List<OrderProduct> orderProductList = new ArrayList<>();
        while (orderListIterator.hasNext()){
            OrderItem item = orderListIterator.next();
            Long itemId = item.getItemId();
            Integer quantity = item.getQuantity();
            Product product = productService.getProductById(itemId);
            product.updateQuantity(quantity);
            OrderProduct orderProduct = new OrderProduct(order, product, quantity);
            orderProductList.add(orderProduct);
        }
        order.setOrderProducts(orderProductList);
        order.setDateCreated(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        addOrderProductToDb(orderProductList, order);
    }

    @Override
    public void cancelOrder(@NotNull Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            Order cancelOrder = order.get();
            cancelOrder.setStatus(OrderStatus.CANCEL);
        }
        else throw new OrderNotFoundException("Order with id "+id+" does not exist");
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Double getOrderValue(Long orderId) {
        Order order = getOrder(orderId);
        return order.getOrderValue();
    }

    public OrderDetails getOrderDetails(Order order){
        List<Product> productList = new ArrayList<>();
        List<OrderProduct> orderProductList = order.getOrderProducts();
        int i = 0;
        for(OrderProduct op: orderProductList){
            OrderProductPk pk = op.getPk();
            Product product = pk.getProduct();
            System.out.println(product.getProduct_name());
            productList.add(product);
            System.out.println(order.getOrderProducts().get(i).getPk().getProduct().getProduct_name());
            System.out.println(order.getId());
            i++;
        }
        OrderDetails orderDetails = new OrderDetails(order.getId(), productList, order.getOrderValue());
        return orderDetails;
    }

    public void addOrderProductToDb(List<OrderProduct> orderProductList, Order order){
        OrderProduct orderProduct = null;
        ListIterator<OrderProduct> listIterator = orderProductList.listIterator();
        while (listIterator.hasNext()){
            orderProduct = listIterator.next();
            orderProduct.getPk().setOrder(order);
            orderProductService.create(orderProduct);
        }
    }
}
