package com.instagrammanager.instagram.Controller;

import java.util.List;

import com.instagrammanager.instagram.Model.Post;
import com.instagrammanager.instagram.Service.ServiceImplementation.PostService;
import com.instagrammanager.instagram.Service.ServiceImplementation.RedisPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    public PostService postService;
    @Autowired
    public RedisPostService redisPostService;
    
    //GET REQUESTS 
    @GetMapping("/getAll")
    public List<Object> getAllPosts() {
        return redisPostService.getAll();
        // return (List<Post>)(Object)postService.getAll();
    }

    @GetMapping("/get/{id}")
    public Object getPost(@PathVariable Long id) {
        Object post = redisPostService.getById(id);
        if(post == null)
            return postService.getById(id);
        else
            return post;
    }

    // POST REQUESTS
    @PostMapping("/addAllPosts")
    public void addAllPosts(@RequestBody List<Post> posts) {
        postService.addAll((List<Object>)(Object)posts);
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        postService.add(post);
        redisPostService.add(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DELETE REQUESTS
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.delete(id);
        redisPostService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
