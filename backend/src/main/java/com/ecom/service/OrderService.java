package com.ecom.service;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, ProductRepository productRepo){this.orderRepo=orderRepo; this.productRepo=productRepo;}

    @Transactional
    public Order createOrder(User user, List<OrderItem> items){
        Order o = new Order();
        o.setUser(user);
        o.setStatus("CREATED");
        BigDecimal total = BigDecimal.ZERO;
        for(OrderItem it : items){
            Product p = productRepo.findById(it.getProduct().getId()).orElseThrow();
            if(p.getStock() < it.getQuantity()) throw new RuntimeException("out of stock");
            p.setStock(p.getStock() - it.getQuantity());
            productRepo.save(p);
            it.setPrice(p.getPrice());
            total = total.add(p.getPrice().multiply(new BigDecimal(it.getQuantity())));
        }
        o.setItems(items);
        o.setTotal(total);
        return orderRepo.save(o);
    }

    public List<Order> listByUser(Long userId){ return orderRepo.findByUserId(userId); }
}

