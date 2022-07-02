package com.instagrammanager.instagram.Service.ServiceImplementation;

import java.util.List;

import com.instagrammanager.instagram.Exception.Exceptions.RecordNotFoundException;
import com.instagrammanager.instagram.Model.Post;
import com.instagrammanager.instagram.Repository.PostRepository;
import com.instagrammanager.instagram.Service.InstagramService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PostService implements InstagramService{
    
    @Autowired
    public PostRepository postRepository;

    // Get Posts
    @Override
    public List<Object> getAll() {
        return (List<Object>)(Object)postRepository.findAll();
    }
    @Override
    @Cacheable(value = "Post", key = "#id")
    public Object getById(Long id) {
        log.info("-----POST FROM DATABASE-----");
        return postRepository.findById(id).orElseThrow(() -> new RecordNotFoundException());
    }

    // Add Posts
    @Override
    public void addAll(List<Object> posts) {
        postRepository.saveAll((List<Post>)(Object)posts);
    }
    @Override
    public void add(Object post) {
        postRepository.save((Post)post);
    }

    // Delete Posts
    @Override
    @CacheEvict(value = "Post", key = "#id")
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
