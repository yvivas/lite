package com.lite.springboot.challenge1.c1sbyevc.controller;

import com.lite.springboot.challenge1.c1sbyevc.model.Product;
import com.lite.springboot.challenge1.c1sbyevc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product",method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return productService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "/product",method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return productService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(path="/product/{product_id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable long product_id){
        return productService.getProductById(product_id);
    }


    @RequestMapping(path="/product/delete/{product_id}", method = RequestMethod.DELETE)
    public Boolean delProductById(@PathVariable long product_id){
        return productService.deleteProduct(product_id);
    }
}
