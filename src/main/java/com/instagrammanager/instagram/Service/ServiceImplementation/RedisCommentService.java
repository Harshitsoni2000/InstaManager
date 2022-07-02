package com.instagrammanager.instagram.Service.ServiceImplementation;

import java.util.List;

import com.instagrammanager.instagram.Repository.RedisCommentRepository;
import com.instagrammanager.instagram.Service.RedisInstagramService;
import com.instagrammanager.instagram.Model.Comment;
import com.instagrammanager.instagram.Model.RedisComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableCaching
@Slf4j
public class RedisCommentService implements RedisInstagramService {

    @Autowired
    public RedisCommentRepository redisCommentRepository;

    @Override
    public List<Object> getAll() {
        return redisCommentRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id", value = "RedisComment")
    public Object getById(Long id) {
        log.info("----------GET COMMENT FROM DATABASE----------");
        return redisCommentRepository.find(id);
    }

    @Override
    public void add(Object object) {
        Comment comment = (Comment)object;
        RedisComment redisComment = new RedisComment(comment.getId(), comment.getText(), comment.getFromUserId(), comment.getFromusername(), comment.getMediaId(), comment.getMediaProductType());
        redisCommentRepository.add(redisComment);
    }

    @Override
    @CacheEvict(key = "#id", value = "RedisPost")
    public void delete(Long id) {
        redisCommentRepository.delete(id);
    }
    
}
