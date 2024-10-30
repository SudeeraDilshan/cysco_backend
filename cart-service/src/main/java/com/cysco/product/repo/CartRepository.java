package com.cysco.product.repo;
import com.cysco.product.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
 Page<Cart> findByUserId(String userId, Pageable pageable);
}
