package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    // Implement the methods defined in the UserService interface

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * Retrieves all users from the database and converts them to UserTO objects.
     *
     * @return a list of UserTO objects representing all users in the database
     * @throws UserNotFoundException if no users are found in the database
     */
    public List<UserTO> getAllUsers() throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.getAllUsers");

       List<User> users = userRepository.findAll();

       if(CollectionUtils.isEmpty(users)) {
           log.error("No users found in the database");
           throw new UserNotFoundException("No users found in the database");
       }

     List<UserTO> userTOS = users.stream().map(user->{
           UserTO userTO = new UserTO(user.getUserId(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword(), user.getRole(), user.getCreatedAt());
           return userTO;
       }).toList();

       log.info("Successfully retrieved the list of users from UserServiceImpl.getAllUsers, total users found: {}", userTOS.size());

       log.info("Returning the list of users from UserServiceImpl.getAllUsers");
       return userTOS;

    }

}
