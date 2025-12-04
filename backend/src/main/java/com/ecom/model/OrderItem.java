package com.ecom.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne private Order order;
    @ManyToOne private Product product;
    private int quantity;
    private BigDecimal price;

    public OrderItem() {}
    public Long getId(){return id;}
    public Order getOrder(){return order;} public void setOrder(Order o){this.order=o;}
    public Product getProduct(){return product;} public void setProduct(Product p){this.product=p;}
    public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
    public BigDecimal getPrice(){return price;} public void setPrice(java.math.BigDecimal p){this.price=p;}
}

