package com.virajadiga.ECommerceApp.services;

import com.virajadiga.ECommerceApp.models.Product;
import com.virajadiga.ECommerceApp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(Long categoryId){
        return productRepository.findAllByCategory_Id(categoryId);
    }
}
