package com.cysco.category.service;

import com.cysco.category.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CategoryService {
    public Page<CategoryDto> getAllcategories(Pageable pageable);
    public CategoryDto getCategoryById(String id);
    public CategoryDto saveCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(String id,CategoryDto categoryDto);
    public void deleteCategory(String id);
    public Mono<Void> deleteRelatedProducts(String categoryId);

}
