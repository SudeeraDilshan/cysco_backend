package com.cysco.product.service;
import org.springframework.transaction.annotation.Transactional;
import com.cysco.product.dto.ProductDto;
import com.cysco.product.entity.Product;
import com.cysco.product.exception.ResourceNotFoundException;
import com.cysco.product.repo.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FilteredProducts filteredProducts;

    @Override
    public Page<ProductDto> getAllProducts(int page,int size,String categoryId,Double minPrice,Double maxPrice,String search,Pageable pageable) {
          Page<Product> products = FilteredProducts.getFilteredProducts(page,size,categoryId,minPrice,maxPrice,search,pageable);
          return products.map(product -> modelMapper.map(product, ProductDto.class));
    }

    @Override
    public ProductDto getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return modelMapper.map(product, ProductDto.class);
        }
        else{
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }

    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String id,ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setName(productDto.getName());
            product.get().setPrice(productDto.getPrice());
            product.get().setDescription(productDto.getDescription());
            product.get().setCategoryId(productDto.getCategoryId());
            product.get().setImage(productDto.getImage());
            Product updatedProduct = productRepository.save(product.get());

            return modelMapper.map(updatedProduct, ProductDto.class);

        }
        else{
            throw new ResourceNotFoundException("Product with id " + productDto.getId() + " not found");
        }
    }

    @Override
    public ProductDto deleteProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return modelMapper.map(product.get(), ProductDto.class);
        }
        else{
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }

    @Transactional
    public void deleteProductsByCategory(String categoryId) {
        productRepository.deleteByCategoryId(categoryId);
    }
}
