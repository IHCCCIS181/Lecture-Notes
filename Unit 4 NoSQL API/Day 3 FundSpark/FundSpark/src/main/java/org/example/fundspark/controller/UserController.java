package org.example.fundspark.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.example.fundspark.exception.PasswordNotCorrectException;
import org.example.fundspark.exception.UsernameNotFoundException;
import org.example.fundspark.model.User;
import org.example.fundspark.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register",  consumes = "application/json")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
        } catch (ConstraintViolationException ve) {
            return ResponseEntity.badRequest().body(ve.getMessage());
        }
    }



//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        try {
//            User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
//            if (loggedInUser != null) {
//                return ResponseEntity.ok(loggedInUser);
//            } else {
//                return ResponseEntity.status(401).body("Invalid username or password");
//            }
//        } catch (UsernameNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (PasswordNotCorrectException e) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//    }
}
