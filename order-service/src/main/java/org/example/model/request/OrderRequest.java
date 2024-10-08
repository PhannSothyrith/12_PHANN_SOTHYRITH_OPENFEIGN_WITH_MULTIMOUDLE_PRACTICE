package org.example.model.request;

import lombok.*;
import org.example.model.Order;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;

    public  Order toEntity (){
        LocalDate orderDate = LocalDate.now();
        return new Order (null,customerId,productIds,orderDate);
    }
    public  Order toEntity (Long id){
        LocalDate orderDate = LocalDate.now();
        return new Order (id,customerId,productIds,orderDate);
    }


}


