package org.example.service;

import org.example.model.Product;
import org.example.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProductById(Long id, ProductRequest productRequest);
    void deleteProductById(Long id);
}
