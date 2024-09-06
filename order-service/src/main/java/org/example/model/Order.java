package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.example.model.response.CustomerResponse;
import org.example.model.response.OrderResponse;
import org.example.model.response.ProductResponse;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ElementCollection
    private List<Long> productIds;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate orderDate;


    public OrderResponse toResponse(CustomerResponse customerResponse , List<ProductResponse> productResponses){
        return new OrderResponse(this.id,customerResponse,productResponses, this.orderDate);
    }


}
