package org.example.model.request;

import lombok.*;
import org.example.model.Product;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductRequest {
    private String name;
    private Double price;

    public Product toEntity() {
        return new Product(null, this.name, this.price);
    }
    public Product toEntity(Long id) {
        return new Product(id, this.name, this.price);
    }
}
