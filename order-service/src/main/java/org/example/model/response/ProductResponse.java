package org.example.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
}
