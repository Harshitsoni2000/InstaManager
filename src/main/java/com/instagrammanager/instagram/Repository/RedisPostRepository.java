package com.instagrammanager.instagram.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.redis.core.RedisTemplate;
import com.instagrammanager.instagram.Model.RedisPost;

@Repository
public class RedisPostRepository {
    
    @Autowired
    public RedisTemplate<String, Object> redisTemplate;

    public static final String KEY = "RedisPost";

    public void add(RedisPost redisPost) {
        redisTemplate.opsForHash().put(KEY, ""+redisPost.getId(), redisPost);
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
