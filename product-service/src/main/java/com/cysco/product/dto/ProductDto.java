package com.cysco.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private String id;
    private String name;
    private double price;
    private String description;
    private String image;
    private String categoryId;
    private String quantity;
}
