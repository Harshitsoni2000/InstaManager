package com.instagrammanager.instagram.Controller;

import java.util.List;

import com.instagrammanager.instagram.Model.Comment;
import com.instagrammanager.instagram.Service.ServiceImplementation.CommentService;
import com.instagrammanager.instagram.Service.ServiceImplementation.RedisCommentService;

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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {
    
    @Autowired
    public CommentService commentService;
    @Autowired
    public RedisCommentService redisCommentService;

    // GET REQUESTS
    @GetMapping("/getAll")
    public List<Object> getAllComment() {
        return redisCommentService.getAll();
        // return (List<Comment>)(Object)commentService.getAll();
    }

    @GetMapping("/get/{id}")
    public Object getComment(@PathVariable Long id) {
        Object c =  redisCommentService.getById(id);
        if(c == null) {
            log.info("FETCHING DATA FROM DATABASE");
            return commentService.getById(id);
        }
        else 
            return c;
    }

    // POST REQUESTS
    @PostMapping("/addAllComments")
    public void addAllComments(@RequestBody List<Comment> comments) {
        commentService.addAll((List<Object>)(Object)comments);
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        commentService.add(comment);
        redisCommentService.add(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DELETE REQUESTS
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        redisCommentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
