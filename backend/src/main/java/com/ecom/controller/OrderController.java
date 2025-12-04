package com.ecom.controller;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import com.ecom.service.OrderService;
import com.ecom.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController @RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ProductService productService;

    public OrderController(OrderService orderService, UserRepository userRepository, ProductService productService){
        this.orderService = orderService; this.userRepository = userRepository; this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,Object> body){
        String username = (String) body.get("username"); // in real apps extract from JWT
        var user = userRepository.findByUsername(username).orElseThrow();
        var itemsRaw = (List<Map<String,Object>>) body.get("items");
        var items = itemsRaw.stream().map(m -> {
            OrderItem it = new OrderItem();
            Product p = productService.get( ((Number) m.get("productId")).longValue() );
            it.setProduct(p);
            it.setQuantity(((Number)m.get("quantity")).intValue());
            return it;
        }).collect(Collectors.toList());
        Order o = orderService.createOrder(user, items);
        return ResponseEntity.ok(Map.of("orderId", o.getId(), "total", o.getTotal()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> listByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.listByUser(userId));
    }
}

