package com.instagrammanager.instagram.Service.ServiceImplementation;

import java.util.List;

import com.instagrammanager.instagram.Exception.Exceptions.RecordNotFoundException;
import com.instagrammanager.instagram.Model.Comment;
import com.instagrammanager.instagram.Model.RedisComment;
import com.instagrammanager.instagram.Repository.CommentRepository;
import com.instagrammanager.instagram.Service.InstagramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CommentService implements InstagramService{
    
    @Autowired
    public CommentRepository commentRepository;

    // Get Comments
    @Override
    public List<Object> getAll() {
        return (List<Object>)(Object)commentRepository.findAll();
    }
    @Override
    @Cacheable(value = "Comment", key = "#id")
    public Object getById(Long id) {
        log.info("-----COMMENT FROM DATABASE-----");
        return commentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException());
    }

    // Add Comments
    @Override
    public void addAll(List<Object> comments) {
        commentRepository.saveAll((List<Comment>)(Object)comments);
    }
    @Override
    public void add(Object object) {
        commentRepository.save((Comment)object);
    } 

    // Delete Comments
    @Override
    @CacheEvict(value = "Comment", key = "#id")
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
