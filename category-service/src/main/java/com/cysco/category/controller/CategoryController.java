package com.cysco.category.controller;
import com.cysco.category.common.SuccessResponse;
import com.cysco.category.dto.CategoryDto;
import com.cysco.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@CrossOrigin
public class CategoryController extends BaseController{

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<CategoryDto>>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryDto> categoryPage = categoryService.getAllcategories(pageable);

        // Wrap the list of products in SuccessResponse
        SuccessResponse<List<CategoryDto>> success = new SuccessResponse<>(
                "Fetched all categories",
                categoryPage.getContent(),
                HttpStatus.OK
        );

        // Return the SuccessResponse with HttpStatus
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<CategoryDto>> getCategoryById(@PathVariable String id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        SuccessResponse<CategoryDto> success = new SuccessResponse<>("fetched catogory",categoryDto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<CategoryDto>> addCateogory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedcategorydto = categoryService.saveCategory(categoryDto);
        SuccessResponse<CategoryDto> success = new SuccessResponse<>("saved catogory",savedcategorydto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<CategoryDto>> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedcategoryDto = categoryService.updateCategory(id, categoryDto);
        SuccessResponse<CategoryDto> success = new SuccessResponse<>("updated catogory",updatedcategoryDto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
         categoryService.deleteCategory(categoryId);
    }

}
