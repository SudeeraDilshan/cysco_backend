package com.cysco.category.entity;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    private String id;
    private String name;
    private String description;
    private String image;
}

