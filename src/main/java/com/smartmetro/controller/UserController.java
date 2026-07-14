package com.smartmetro.controller;


import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/api/v1/users GET
    @GetMapping
    public ResponseEntity<List<UserTO>> getAllUsers(){
        log.info("Inside the UserController.getAllUsers");
        List<UserTO> userTOS = null;
        try{
            userTOS = userService.getAllUsers();
        } catch (UserNotFoundException ex){
            log.error("User not found exception occurred in UserController.getAllUsers: {}", ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex1){
            log.error("Exception while retrieving users in UserController.getAllUsers: {}", ex1);
            return ResponseEntity.internalServerError().build();
        }

        log.info("Successfully retrieved the list of users from UserController.getAllUsers, total users found: {}", userTOS.size());
        log.info("Returning the list of users from UserController.getAllUsers");
        return ResponseEntity.ok(userTOS);
    }
}
