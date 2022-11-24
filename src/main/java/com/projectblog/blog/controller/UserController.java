package com.projectblog.blog.controller;

import com.projectblog.blog.entities.UserEntity;
import com.projectblog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins= "*")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    @ResponseBody
    public List<UserEntity> getUsers() {
        List<UserEntity> userList = repository.findAll();
        return userList;
    }
    /* Log et vérifie que le pseudo et le password correspondent*/
    @GetMapping("/userlogin")
    @ResponseBody
    public UserEntity login(@RequestBody UserEntity lUser) throws Exception, UsernameNotFoundException {
        UserEntity user = repository.findByPseudo(lUser.getPseudo());
        if(user == null)  {
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }
        if(!user.getPassword().equals(lUser.getPassword())) {
            throw new Exception("Mauvais mot de passe");
        }
        return user;
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
