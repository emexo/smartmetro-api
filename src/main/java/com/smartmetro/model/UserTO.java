package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTO {
    int userId;
    String name;
    String email;
    String phone;
    String password;
    String role;
    String createdAt;
    List<MetroCardTO> metroCardTOList;
}


