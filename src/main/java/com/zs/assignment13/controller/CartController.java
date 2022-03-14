package com.zs.assignment13.controller;

import com.zs.assignment13.entity.CartProduct;
import com.zs.assignment13.entity.CollectionOfId;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import com.zs.assignment13.service.CartService;
import com.zs.assignment13.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;
    private static final Logger logger = LogManager.getLogger(ProductService.class.getName());

    public CartController() {
        cartService = new CartService();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCart(@RequestParam int id) {
        List<CartProduct> result = null;
        try {
            result = cartService.getCart(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> insertIntoCart(@RequestBody CollectionOfId collectionOfId) {
        try {
            cartService.addProductIntoCart(collectionOfId.getProductId(), collectionOfId.getUserId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductFromCart(@RequestBody CollectionOfId collectionOfId) {
        try {
            cartService.removeProductFromCart(collectionOfId.getUserId(), collectionOfId.getProductId());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
