package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testGetAllUsers_whenUserExist_thenReturnData() throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUserId(1);
        user.setName("test");
        user.setEmail("testemail");
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        List<UserTO> userTOS = userService.getAllUsers();
        assertEquals(1, userTOS.size());
    }

    @Test
    public void testGetAllUsers_whenUserNotExist_thenThrowException(){
        List<User> users = null;
        when(userRepository.findAll()).thenReturn(users);

        assertThrows(UserNotFoundException.class, ()->userService.getAllUsers() );
    }
}
