package com.ecom.config;

import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner seed(ProductRepository productRepository) {
        return args -> {
            if(productRepository.count() == 0) {
                Product p1 = new Product(); p1.setName("Wireless Headphones"); p1.setDescription("Noise-cancelling over-ear headphones"); p1.setPrice(new BigDecimal("79.99")); p1.setStock(50);
                Product p2 = new Product(); p2.setName("Mechanical Keyboard"); p2.setDescription("RGB backlit mechanical keyboard"); p2.setPrice(new BigDecimal("59.99")); p2.setStock(30);
                productRepository.save(p1); productRepository.save(p2);
            }
        };
    }
}

