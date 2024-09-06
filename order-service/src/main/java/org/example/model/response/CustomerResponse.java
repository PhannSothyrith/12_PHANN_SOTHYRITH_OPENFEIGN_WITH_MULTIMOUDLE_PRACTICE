package org.example.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
}
