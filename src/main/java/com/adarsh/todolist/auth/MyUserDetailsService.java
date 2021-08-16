package com.adarsh.todolist.auth;

import com.adarsh.todolist.model.Users;
import com.adarsh.todolist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: "+username));
        return user.map(MyUserDetails::new).get();
    }
}
