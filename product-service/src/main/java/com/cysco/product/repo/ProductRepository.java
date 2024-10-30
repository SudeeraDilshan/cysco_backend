package com.cysco.product.repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cysco.product.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    void deleteByCategoryId(String categoryId);
}
