package com.cysco.category.service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.cysco.category.dto.CategoryDto;
import com.cysco.category.entity.Category;
import com.cysco.category.exception.ResourceNotFoundexception;
import com.cysco.category.repo.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  WebClient.Builder webClientBuilder;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Page<CategoryDto> getAllcategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> modelMapper.map(category, CategoryDto.class));
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return modelMapper.map(category, CategoryDto.class);
        }
        else{
            throw new ResourceNotFoundexception("Cateogory with id " + id + " not found");
        }

    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCateogory = categoryRepository.save(category);
        return modelMapper.map(savedCateogory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String id,CategoryDto categoryDto) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setName(categoryDto.getName());
            category.get().setDescription(categoryDto.getDescription());
            category.get().setImage(categoryDto.getImage());
            Category updatedCategory = categoryRepository.save(category.get());

            return modelMapper.map(updatedCategory, CategoryDto.class);

        }
        else{
            throw new ResourceNotFoundexception("Cateogory with id " + id + " not found");
        }
    }

    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Delete related products
        deleteRelatedProducts(id).subscribe();

        // Delete the category
        categoryRepository.delete(category);
    }

     public Mono<Void> deleteRelatedProducts(String categoryId) {
        return webClientBuilder.build()
                .delete()
                .uri("http://localhost:8080/api/v1/product/category/{categoryId}", categoryId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
