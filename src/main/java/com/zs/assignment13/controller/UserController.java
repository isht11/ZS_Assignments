package com.zs.assignment13.controller;

import com.zs.assignment13.entity.User;
import com.zs.assignment13.exceptions.InternalServerException;
import com.zs.assignment13.exceptions.NotFoundError;
import com.zs.assignment13.exceptions.NotValidException;
import com.zs.assignment13.service.ProductService;
import com.zs.assignment13.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    private static final Logger logger = LogManager.getLogger(ProductService.class.getName());

    public UserController() {
        userService = new UserService();
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers() {
        List<User> result;
        try {
            result = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NotFoundError | NotValidException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (InternalServerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
