package com.smartmetro.model;

public record UserTO(
     int userId,

     String name,

     String email,

     String phone,

     String password,

     String role,

     String createdAt){}

