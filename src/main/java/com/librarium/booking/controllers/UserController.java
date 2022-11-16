package com.librarium.booking.controllers;

import com.librarium.booking.models.User;
import com.librarium.booking.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> list(@RequestParam int page){
        return ResponseEntity.ok(this.userService.list(page));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.save(user));
    }

}
