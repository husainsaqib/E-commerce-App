package com.ecom.service;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){this.repo=repo;}
    public List<Product> list(){ return repo.findAll(); }
    public Product save(Product p){ return repo.save(p); }
    public Product get(Long id){ return repo.findById(id).orElseThrow(); }
}

