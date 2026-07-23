package com.smartmetro.controller;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    public void getAllUsersTest() throws Exception {
        List<UserTO> userTOS = new ArrayList<>();
        UserTO userTO = new UserTO(1, "name", "email", "900000","pass","role","create");
        userTOS.add(userTO);

        when(userService.getAllUsers()).thenReturn(userTOS);

        mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk());
    }

    @Test
    public void getAllUsersTest1() throws Exception {

        when(userService.getAllUsers()).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/v1/users")).andExpect(status().isNotFound());
    }

    @Test
    public void getAllUsersTest3() throws Exception {

        when(userService.getAllUsers()).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/api/v1/users")).andExpect(status().isInternalServerError());
    }
}
