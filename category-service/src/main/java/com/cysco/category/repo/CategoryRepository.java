package com.cysco.category.repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cysco.category.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
