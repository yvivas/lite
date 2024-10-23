package com.lite.springboot.challenge1.c1sbyevc.service;

import com.lite.springboot.challenge1.c1sbyevc.exception.ProductException;
import com.lite.springboot.challenge1.c1sbyevc.model.Product;
import com.lite.springboot.challenge1.c1sbyevc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.repo = productRepository;
    }

    /**
     * getting all Product record by using the method findAll() of CrudRepository
     * */
    public List<Product> getAllProducts()
    {
        return new ArrayList<>(repo.findAll());
    }
    /**
     * getting a specific record by using the method findById() of CrudRepository 
     * */ 
    public Product getProductById(long id) {
        Product product;
        if(repo.findById(id).isPresent()){
            product = repo.findById(id).get();
        }else{
            throw new ProductException("Product not found");
        }
        return product;
    }
    /** 
     * saving a specific record by using the method save() of CrudRepository 
     * */ 
    public Product saveOrUpdateProduct(Product product)
    {
        if(product != null){
            if(product.getId() != null){
                if(repo.findById(product.getId()).isPresent()){
                    Product productDB = repo.findById(product.getId()).get();
                    if(product.getName() != null && !product.getName().isEmpty()){
                        productDB.setName(product.getName());
                    }
                    if(product.getDescription() != null && !product.getDescription().isEmpty()){
                        productDB.setDescription(product.getDescription());
                    }
                    if(product.getPrice().intValue() > 0 ){
                        productDB.setPrice(product.getPrice());
                    }
                    return repo.save(productDB);
                }
            }else{
                return repo.save(product);
            }
        }else{
            throw new ProductException("Product is null");
        }
        return null;
    }
    /**
     * deleting a specific record by using the method deleteById() of CrudRepository
     * */  
    public boolean deleteProduct(long id) {
        boolean isProductDeleted = false;
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
            if(repo.findById(id).isPresent()){
                isProductDeleted = true;
            }
        }else{
            throw new ProductException("Product not found, cannot be deleted ");
        }
        return isProductDeleted;
    }


}
