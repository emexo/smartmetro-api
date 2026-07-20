package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest{

    String name;

    String email;

    String phone;

    String password;

    String role;

    String createdAt;
}

