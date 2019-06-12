package com.springboot.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pk.order", cascade = CascadeType.REMOVE)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime dateCreated;

    @NotNull
    private String status;

    @JsonIgnore
    public double getOrderValue(){
        double orderValue = 0D;
        ListIterator<OrderProduct> orderProductIterator = this.getOrderProducts().listIterator();
        while ((orderProductIterator.hasNext())){
            OrderProduct product = orderProductIterator.next();
            orderValue+=product.getTotalPrice();
        }
        return orderValue;
    }

    @JsonIgnore
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }
    
}
