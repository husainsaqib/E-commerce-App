package com.ecom.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne private User user;
    private BigDecimal total;
    private String status;
    private Instant createdAt = Instant.now();
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) private List<OrderItem> items = new ArrayList<>();

    public Order() {}
    public Long getId(){return id;}
    public User getUser(){return user;} public void setUser(User u){this.user=u;}
    public BigDecimal getTotal(){return total;} public void setTotal(BigDecimal t){this.total=t;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){ this.items = items; for(var i:items) i.setOrder(this); }
}

