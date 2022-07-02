package com.instagrammanager.instagram.RabbitMQ.Consumer;

import com.instagrammanager.instagram.Constants.Constant;
import com.instagrammanager.instagram.Model.Comment;
import com.instagrammanager.instagram.Service.ServiceImplementation.CommentService;
import com.instagrammanager.instagram.Service.ServiceImplementation.RedisCommentService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommentConsumer {

    @Autowired
    public CommentService commentService;
    @Autowired
    public RedisCommentService redisCommentService;
    
    @RabbitListener(queues = Constant.commentQueue)
    public void consumeObject(Comment comment) {
        // Print the Comment received through Comment_Queue
        log.info("Message Received through Comment Queue : " + comment);
        
        // Save the comment in the Comment Table
        redisCommentService.add(comment);
        commentService.add(comment);
    }
}
