package com.instagrammanager.instagram.RabbitMQ.Consumer;

import com.instagrammanager.instagram.Constants.Constant;
import com.instagrammanager.instagram.Model.Post;
import com.instagrammanager.instagram.Service.ServiceImplementation.PostService;
import com.instagrammanager.instagram.Service.ServiceImplementation.RedisPostService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PostConsumer {
    
    @Autowired
    public PostService postService;
    @Autowired
    public RedisPostService redisPostService;
    
    @RabbitListener(queues = Constant.postQueue)
    public void consumeObject(Post post) {
        // Print the Post received through Post_Queue
        log.info("Message Received through Post Queue : " + post);

        // Save the Post in Post Table
        redisPostService.add(post);
        postService.add(post);
    }
}
