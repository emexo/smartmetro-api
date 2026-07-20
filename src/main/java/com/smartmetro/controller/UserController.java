package com.smartmetro.controller;


import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // http://localhost:8080/api/v1/users POST
    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody UserRequest userRequest){
        log.info("Inside the UserController.save, userRequest:{}", userRequest);

        int userId = 0;
        try{
            userId = userService.save(userRequest);
            log.info("Saved User, userTO:{}", userId);
        } catch (UserNotFoundException ex){
            log.error("User not found exception occurred in UserController.getAllUsers: {}", ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex1){
            log.error("Exception while retrieving users in UserController.getAllUsers: {}", ex1);
            return ResponseEntity.internalServerError().build();
        }
        log.info("End of UserController.save");
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

}
