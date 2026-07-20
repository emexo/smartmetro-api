package com.smartmetro.service;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;

import java.util.List;

public interface UserService {

    // Define the methods that will be implemented in the UserServiceImpl class
    List<UserTO> getAllUsers() throws UserNotFoundException;

    int save(UserRequest userRequest) throws UserNotFoundException;
}
