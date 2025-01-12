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
public class Cart {
    @Id
    private String id;
    private String userId;
    private String quantity;
}
