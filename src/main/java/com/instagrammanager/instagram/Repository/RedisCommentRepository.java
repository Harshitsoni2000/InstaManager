package com.instagrammanager.instagram.Repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.instagrammanager.instagram.Model.RedisComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Repository
public class RedisCommentRepository {
    
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    public static final String KEY = "RedisComment";

    public void add(RedisComment redisComment) {
        redisTemplate.opsForHash().put(KEY, ""+redisComment.getId(), redisComment);
    }

    public Object find(Long id) {
        return redisTemplate.opsForHash().get(KEY, ""+id);
    }

    public List<Object> findAll() {
        return redisTemplate.opsForHash().values(KEY);
    }

    public void delete(Long id) {
        redisTemplate.opsForHash().delete(KEY, ""+id);
    }
}
