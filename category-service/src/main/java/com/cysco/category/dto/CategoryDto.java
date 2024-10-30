package com.cysco.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private String id;
    private String name;
    private String description;
    private String image;
}
