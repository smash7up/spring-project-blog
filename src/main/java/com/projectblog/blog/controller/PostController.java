package com.projectblog.blog.controller;

import com.projectblog.blog.entities.Post;
import com.projectblog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPost() {
        List<Post> postList = repository.findAll();
        return postList;
    }

    @GetMapping("/post")
    @ResponseBody
    public Post getPost(@RequestParam(required = false) Long id) {
        Post post = new Post();
        if (id != null) {
            Optional<Post> optionalPost = repository.findById(id);
            if (optionalPost.isPresent()) {
                post = optionalPost.get();
            }
        }
        return post;
    }

    @PostMapping("/post/create")
    public void createPost(@RequestBody Post post) {
        Post newPost = repository.save(post);
    }

    @DeleteMapping("/post/delete")
    public String deletePost(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/post";
    }
}
