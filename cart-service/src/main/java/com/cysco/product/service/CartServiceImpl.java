package com.cysco.product.service;
import com.cysco.product.entity.Cart;
import org.springframework.transaction.annotation.Transactional;
import com.cysco.product.dto.CartDto;
import com.cysco.product.exception.ResourceNotFoundException;
import com.cysco.product.repo.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CartDto> getAllCartsByUserId(String id,Pageable pageable) {
        Page<Cart> products = cartRepository.findByUserId(id,pageable);
        return products.map(cart -> modelMapper.map(cart, CartDto.class));
    }

    @Override
    public CartDto getCartById(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return modelMapper.map(cart, CartDto.class);
        }
        else{
            throw new ResourceNotFoundException("Cart with id " + id + " not found");
        }

    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        Cart cart = modelMapper.map(cartDto, Cart.class);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDto.class);
    }

    @Override
    public CartDto updateCart(String id, CartDto cartDto) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            Cart newcart = modelMapper.map(cartDto, Cart.class);
            newcart.setId(id);
            Cart updatedCart = cartRepository.save(newcart);
            return modelMapper.map(updatedCart, CartDto.class);
        }
        else{
            throw new ResourceNotFoundException("Cart with id " + id + " not found");
        }
    }

    @Override
    public CartDto deleteCart(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            cartRepository.deleteById(id);
            return modelMapper.map(cart.get(), CartDto.class);
        }
        else{
            throw new ResourceNotFoundException("Cart with id " + id + " not found");
        }
    }

}
