package com.projectblog.blog.controller;

import com.projectblog.blog.entities.PostEntity;
import com.projectblog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping("/posts")
    @ResponseBody
    public List<PostEntity> getPost() {
        List<PostEntity> postList = repository.findAll();
        return postList;
    }

    @GetMapping("/post")
    @ResponseBody
    public PostEntity getPost(@RequestParam(required = false) Long id) {
        PostEntity post = new PostEntity();
        if (id != null) {
            Optional<PostEntity> optionalPost = repository.findById(id);
            if (optionalPost.isPresent()) {
                post = optionalPost.get();
            }
        }
        return post;
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody PostEntity post) {
        PostEntity newPost = repository.save(post);
    }

    @DeleteMapping("/posts")
    public String deletePost(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/post";
    }
}
