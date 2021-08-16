package com.adarsh.todolist.controller;

import com.adarsh.todolist.model.Users;
import com.adarsh.todolist.repo.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        // Find all the users
        @GetMapping("/all")
        public List<Users> user(){
            return userRepository.findAll();
        }

        @CrossOrigin(origins = "http://localhost:4200")
        @GetMapping("/{username}")
        public ResponseEntity<Users> getUserByName(@PathVariable String username) throws Exception
        {
            Optional<Users> user = userRepository.findByUserName(username);
            user.orElseThrow(() -> new Exception("User: "+username+" not found"));
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }

        // Find users by Id
        @GetMapping("/find/{id}")
        public Users getUser(@PathVariable int id) throws Exception{
            Optional<Users> user = userRepository.findById(id);
            user.orElseThrow(() -> new Exception("User with id: "+id+" not found"));
            return user.get();
        }

        // Add a user
        @PostMapping(value = "/add")
        public Users save(@Validated @NotNull @RequestBody Users users) throws Exception {
            Optional<Users> user = userRepository.findByUserName(users.getUserName());
            if(user.isPresent())
                throw new Exception("User with username: "+users.getUserName()+" already exists!");
            user = userRepository.findByEmailId(users.getEmailId());
            if(user.isPresent())
                throw new Exception("User with email id: "+users.getEmailId()+" already exists!");
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            return userRepository.save(users);
        }
        @PutMapping(value = "/update")
        public Users update(@Validated @NotNull @RequestBody Users users) throws Exception {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            return userRepository.save(users);
        }

        // Delete a user
        @DeleteMapping(value = "/delete/{id}")
        public void delete(@PathVariable int id)
        {
            userRepository.deleteById(id);
        }

}

// name used in query
