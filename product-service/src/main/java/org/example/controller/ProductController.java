package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.model.response.ApiResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .message("Product created successfully")
                .payload(product)
                .status(HttpStatus.CREATED)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .message("Get product by id " + " successfully")
                .payload(product)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProduct (){
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .message("Get all products successfully")
                .payload(products)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Product product = productService.updateProductById(id, productRequest);
        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .message("Product updated with id " + " successfully")
                .payload(product)
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .message("Product deleted with id " + " successfully")
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
