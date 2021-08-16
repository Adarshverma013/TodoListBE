package com.adarsh.todolist.repo;

import com.adarsh.todolist.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByEmailId(String emailId);
    void deleteByUserName(String userName);
}
