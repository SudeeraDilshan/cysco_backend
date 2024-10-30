package com.cysco.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {
    private String id;
    private String userId;
    private List<CartItemDto> cartItems;
}
