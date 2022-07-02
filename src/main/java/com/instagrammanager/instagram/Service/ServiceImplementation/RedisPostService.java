package com.instagrammanager.instagram.Service.ServiceImplementation;

import java.util.List;

import com.instagrammanager.instagram.Service.RedisInstagramService;
import com.instagrammanager.instagram.Model.Post;
import com.instagrammanager.instagram.Model.RedisPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.instagrammanager.instagram.Repository.RedisPostRepository;

@Service
@EnableCaching
@Slf4j
public class RedisPostService implements RedisInstagramService{

    @Autowired
    public RedisPostRepository redisPostRepository;

    @Override
    public List<Object> getAll() {
        return redisPostRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id", value = "RedisPost")
    public Object getById(Long id) {
        log.info("----------GET POST FROM DATABASE----------");
        return redisPostRepository.find(id);
    }

    @Override
    public void add(Object object) {
        Post post = (Post)object;
        RedisPost redisPost = new RedisPost(post.getId(), post.getRecipientId(), post.getSenderId(), post.getMessageMid(), post.getMessageText());
        redisPostRepository.add(redisPost);
    }

    @Override
    @CacheEvict(key = "#id", value = "RedisPost")
    public void delete(Long id) {
        redisPostRepository.delete(id);
    }
    
}
