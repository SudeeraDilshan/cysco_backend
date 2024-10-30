package com.cysco.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemDto {
    private String productId;
    private String quantity;
    private double unitPrice;
}
