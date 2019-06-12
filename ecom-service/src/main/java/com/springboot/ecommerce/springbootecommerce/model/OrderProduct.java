package com.springboot.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class OrderProduct{

    @EmbeddedId
    @JsonIgnore
    private OrderProductPk pk;

    @Min(1)
    private Integer quantity;

    public OrderProduct(Order order, Product product, Integer qty){
        pk = new OrderProductPk();
        pk.setOrder(order);
        pk.setProduct(product);
        this.quantity = qty;
    }

    @Transient
    public Product getProduct(){
        return this.pk.getProduct();
    }

    @Transient
    public Double getTotalPrice(){
        return getProduct().getPrice()*quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct)) return false;
        OrderProduct that = (OrderProduct) o;
        return pk.equals(that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
