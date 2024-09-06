package org.example.model.request;
import lombok.*;
import org.example.model.Customer;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerRequest {
    private String name;
    private String email;
    public Customer toEntity(){
        return  new Customer(null,this.name, this.email);
    }
    public Customer toEntity(Long id){
        return  new Customer(id,this.name, this.email);
    }


}

