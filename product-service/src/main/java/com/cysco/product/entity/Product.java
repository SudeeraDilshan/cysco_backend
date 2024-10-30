package com.cysco.product.entity;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    private String image;
    private String categoryId;
    private String quantity;
}
