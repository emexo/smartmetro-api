package com.smartmetro.repository;

import com.smartmetro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByName(String name);

    @Query(value = "SELECT u FROM User u WHERE u.name = ?1")
    List<User> findByUserName(String name);
}
