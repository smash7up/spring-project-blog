package com.projectblog.blog.controller;

import com.projectblog.blog.entities.User;
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
    public List<User> getUsers() {
        List<User> userList = repository.findAll();
        return userList;
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUserById(@RequestParam(required = false) Long id) {
        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = repository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        // model.addAttribute("user", user); A UTILISER LORSQUE THYMELEAF EST UTILISE

        return user;
    }

    @PostMapping("/user/login")
    public void postUser(@RequestBody User user) {
       User newUser = repository.save(user);
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/user";
    }
}
