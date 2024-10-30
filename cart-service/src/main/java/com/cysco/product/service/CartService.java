package com.cysco.product.service;
import com.cysco.product.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartService {

    public Page<CartDto> getAllCartsByUserId(String id,Pageable pageable);
    public CartDto getCartById(String id);
    public CartDto saveCart(CartDto cartDto);
    public CartDto updateCart(String id, CartDto cartDto);
    public CartDto deleteCart(String id);
}
