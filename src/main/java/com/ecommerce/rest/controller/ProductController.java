package com.ecommerce.rest.controller;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    private ProductService productService;

    @GetMapping("list")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("delete")
    public Product deleteProduct(@RequestParam String productName){
        return productService.deleteProduct(productName);
    }

    @PutMapping("save")
    public boolean addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PostMapping("update")
    public boolean updateProduct(@RequestParam String productToUpdate, @RequestBody Product updatedProduct){
        return productService.update(productToUpdate, updatedProduct);
    }
}
