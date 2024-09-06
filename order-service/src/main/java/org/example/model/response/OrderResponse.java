package org.example.model.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderResponse {
    private Long id;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productResponses;

}
