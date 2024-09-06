package org.example.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found"));

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProductById(Long id, ProductRequest productRequest) {
        if (productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product not found");
        }
        Product product = productRequest.toEntity(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
