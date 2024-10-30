package com.cysco.product.controller;
import com.cysco.product.dto.ProductDto;
import com.cysco.product.common.SuccessResponse;
import com.cysco.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProductDto>>> getAllProducts(
            @RequestParam(value ="categoryId", required = false) String categoryId,
            @RequestParam(value ="sortBy" , defaultValue = "name") String sortBy,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size,sort);
        Page<ProductDto> productPage = productService.getAllProducts(page,size,categoryId,minPrice,maxPrice,search,pageable);

        // Wrap the list of products in SuccessResponse
        SuccessResponse<List<ProductDto>> success = new SuccessResponse<>(
                "Fetched all products",
                productPage.getContent(),
                HttpStatus.OK
        );

        // Return the SuccessResponse with HttpStatus
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDto>> getProductById(@PathVariable String id) {
        ProductDto productDto = productService.getProductById(id);
        SuccessResponse<ProductDto> success = new SuccessResponse<>("fetched product",productDto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<ProductDto>> addProduct(@RequestBody ProductDto productDto) {
        ProductDto savedproductdto = productService.saveProduct(productDto);
        SuccessResponse<ProductDto> success = new SuccessResponse<>("saved product",savedproductdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDto>> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        ProductDto updatedproductdto = productService.updateProduct(id, productDto);
        SuccessResponse<ProductDto> success = new SuccessResponse<>("updated product",updatedproductdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDto>> deleteProduct(@PathVariable String id) {
        ProductDto deletedproductdto = productService.deleteProduct(id);
        SuccessResponse<ProductDto> success = new SuccessResponse<>("deleted product",deletedproductdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteProductsByCategory(@PathVariable String categoryId) {
        productService.deleteProductsByCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    
}
