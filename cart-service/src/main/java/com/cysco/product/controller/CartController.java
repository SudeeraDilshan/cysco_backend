package com.cysco.product.controller;
import com.cysco.product.dto.CartDto;
import com.cysco.product.common.SuccessResponse;
import com.cysco.product.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<CartDto>>> getAllCartsByUserId(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CartDto> cartPage = cartService.getAllCartsByUserId(userId,pageable);

        // Wrap the list of products in SuccessResponse
        SuccessResponse<List<CartDto>> success = new SuccessResponse<>(
                "Fetched all carts",
                cartPage.getContent(),
                HttpStatus.OK
        );

        // Return the SuccessResponse with HttpStatus
        return new ResponseEntity<>(success, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<CartDto>> getCartById(@PathVariable String id) {
        CartDto cartDto = cartService.getCartById(id);
        SuccessResponse<CartDto> success = new SuccessResponse<>("fetched cart", cartDto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SuccessResponse<CartDto>> addCart(@RequestBody CartDto cartDto) {
        CartDto savedcartdto = cartService.saveCart(cartDto);
        SuccessResponse<CartDto> success = new SuccessResponse<>("saved cart",savedcartdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<CartDto>> updateCart(@PathVariable String id, @RequestBody CartDto cartDto) {
        CartDto updatedcartdto = cartService.updateCart(id, cartDto);
        SuccessResponse<CartDto> success = new SuccessResponse<>("updated product",updatedcartdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<CartDto>> deleteCart(@PathVariable String id) {
        CartDto deletedcartdto = cartService.deleteCart(id);
        SuccessResponse<CartDto> success = new SuccessResponse<>("deleted cart",deletedcartdto,HttpStatus.OK);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
