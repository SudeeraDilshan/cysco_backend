package com.cysco.product.service;

import com.cysco.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilteredProducts {

    private static MongoTemplate mongoTemplate;

    public FilteredProducts(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public static Page<Product> getFilteredProducts(int page,int size,String categoryId,Double minPrice,Double maxPrice,String search,Pageable pageable) {

        Query query = new Query();

        // ... add criteria as before ...
        if (search != null && !search.isEmpty()) {
            Criteria searchCriteria = new Criteria().andOperator(
                    Criteria.where("name").regex(search, "i"),
                    Criteria.where("categoryId").is(categoryId)
            );
            query.addCriteria(searchCriteria);
        }

        // Filter by  category
        if (categoryId != null && !categoryId.isEmpty()) {
            query.addCriteria(Criteria.where("categoryId").is(categoryId));
        }

        // Price range
        if (minPrice != null || maxPrice != null) {
            Criteria priceCriteria = new Criteria();
            if (minPrice != null) {

                 priceCriteria = priceCriteria.andOperator(
                        Criteria.where("price").gte(minPrice),
                        Criteria.where("categoryId").is(categoryId)
                );
            }
            if (maxPrice != null) {

                 priceCriteria = priceCriteria.andOperator(
                        Criteria.where("price").lte(maxPrice),
                        Criteria.where("categoryId").is(categoryId)
                );
            }
            query.addCriteria(priceCriteria);
        }

        long total = mongoTemplate.count(query, Product.class);
        query.with(pageable);
        query.skip((long) page * size).limit(size);

        List<Product> products = mongoTemplate.find(query, Product.class);

        return new PageImpl<>(products, PageRequest.of(page, size), total);

    }
}
