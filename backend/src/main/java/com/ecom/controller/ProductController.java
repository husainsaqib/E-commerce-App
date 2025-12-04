package com.ecom.controller;
import com.ecom.model.Product;
import com.ecom.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController @RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService){this.productService=productService;}
    @GetMapping public ResponseEntity<List<Product>> list(){ return ResponseEntity.ok(productService.list()); }
    @PostMapping public ResponseEntity<Product> create(@RequestBody Product p){ return ResponseEntity.ok(productService.save(p)); }
    @GetMapping("/{id}") public ResponseEntity<Product> get(@PathVariable Long id){ return ResponseEntity.ok(productService.get(id)); }
}

