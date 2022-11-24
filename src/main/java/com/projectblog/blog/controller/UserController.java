package com.projectblog.blog.controller;

import com.projectblog.blog.entities.UserEntity;
import com.projectblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    @ResponseBody
    public List<UserEntity> getUsers() {
        List<UserEntity> userList = repository.findAll();
        return userList;
    }

    @GetMapping("/user")
    @ResponseBody
    public UserEntity getUserById(@RequestParam(required = false) Long id) {
        UserEntity user = new UserEntity();
        if (id != null) {
            Optional<UserEntity> optionalUser = repository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        // model.addAttribute("user", user); A UTILISER LORSQUE THYMELEAF EST UTILISE

        return user;
    }

    @PostMapping("/users")
    public void postUser(@RequestBody UserEntity user) {
       UserEntity newUser = repository.save(user);
    }

    @DeleteMapping("/users")
    public String deleteUser(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/user";
    }
}
