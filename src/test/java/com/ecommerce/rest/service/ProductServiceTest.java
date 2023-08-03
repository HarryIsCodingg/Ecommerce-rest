package com.ecommerce.rest.service;

import com.ecommerce.rest.model.Product;
import com.ecommerce.rest.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(); // Add your own initialization data
        Product product2 = new Product(); // Add your own initialization data

        // Initialization Data
        product1.setName("pear");
        product2.setName("peach");
        product1.setCategory("fruit");
        product2.setCategory("fruit");
        product1.setImageUrl("pear.png");
        product2.setImageUrl("peach.png");
        product1.setQuantity("10");
        product2.setQuantity("10");
        product1.setPricePerPound("3");
        product2.setPricePerPound("3");
        // Initialization Data

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        // Print
        System.out.println(products);
        System.out.println(result);
        // Print

        assertEquals(products.size(), result.size());
        assertEquals(products, result);
    }

    @Test
    public void testDeleteProduct() {
        String productName = "productName";
        Product product = new Product(); // Add your own initialization data

        // Initialization Data
        product.setName("havarti cheese");
        product.setCategory("dairy");
        product.setImageUrl("havarti.png");
        product.setQuantity("10");
        product.setPricePerPound("3");
        // Initialization Data

        when(productRepository.deleteProductByName(productName)).thenReturn(product);

        Product result = productService.deleteProduct(productName);

        // Print
        System.out.println(product);
        System.out.println(result);
        // Print

        assertEquals(product, result);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product(); // Add your own initialization data

        // Initialization Data
        product.setName("capon");
        product.setCategory("poultry");
        product.setImageUrl("capon.png");
        product.setQuantity("10");
        product.setPricePerPound("3");
        // Initialization Data

        when(productRepository.save(product)).thenReturn(product);

        boolean result = productService.saveProduct(product);

        // Print
        System.out.println("It is " + result + " that the product has been saved.");
        // Print

        assertTrue(result);
    }

    @Test
    public void testUpdateProduct() {
        String productToUpdate = "productToUpdate";
        Product updatedProduct = new Product(); // Add your own initialization data

        // Initialization Data
        updatedProduct.setName("kobe");
        updatedProduct.setCategory("beef");
        updatedProduct.setImageUrl("kobe.png");
        updatedProduct.setQuantity("10");
        updatedProduct.setPricePerPound("3");
        // Initialization Data

        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        boolean result = productService.update(productToUpdate, updatedProduct);

        // Print
        System.out.println("It is " + result + " that the product has been updated.");
        // Print

        assertTrue(result);
        verify(productRepository).deleteProductByName(productToUpdate);
    }
}