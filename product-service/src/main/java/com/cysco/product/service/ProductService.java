package com.cysco.product.service;
import com.cysco.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public Page<ProductDto> getAllProducts(int page,int size,String categoryId,Double minPrice,Double maxPrice,String search,Pageable pageable);
    public ProductDto getProductById(String id);
    public ProductDto saveProduct(ProductDto productDto);
    public ProductDto updateProduct(String id,ProductDto productDto);
    public ProductDto deleteProduct(String id);
    public void deleteProductsByCategory(String categoryId);
}
