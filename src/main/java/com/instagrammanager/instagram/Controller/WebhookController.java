package com.instagrammanager.instagram.Controller;

import com.instagrammanager.instagram.Model.Comment;
import com.instagrammanager.instagram.Model.Post;
import com.instagrammanager.instagram.RabbitMQ.Publisher.CommentPublisher;
import com.instagrammanager.instagram.RabbitMQ.Publisher.PostPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/webhooks")
public class WebhookController {

    @Autowired
    public CommentPublisher commentPublisher;
    @Autowired
    public PostPublisher postPublisher;

    static Long id = (long)1;
    
    // Verify Token from GET REQUESTS
    @GetMapping("")
    public String getWebApi(
    @RequestParam(value="hub.mode", defaultValue = "") String mode, 
    @RequestParam(value="hub.verify_token", defaultValue = "") String verifyToken, 
    @RequestParam(value="hub.challenge", defaultValue = "") String challenge) {
        if(verifyToken.equals("*123*1#"))
            return challenge;
        return "";
    }

    // Recieve Messages from POST REQUESTS
    @PostMapping("")
    public void postWebApi(@RequestBody String messages) {
        log.info(messages);
        try {
            JSONObject jsonObject = new JSONObject(messages).getJSONArray("entry").getJSONObject(0);
            
            if(jsonObject.has("changes")) {
                
                JSONObject value = jsonObject.getJSONArray("changes").getJSONObject(0).getJSONObject("value");

                // Create Comment
                Comment comment = new Comment();
                comment.setId(Long.parseLong(value.getString("id")));
                comment.setText(value.getString("text"));
                comment.setFromUserId(Long.parseLong(value.getJSONObject("from").getString("id")));
                comment.setFromusername(value.getJSONObject("from").getString("username"));
                comment.setMediaId(Long.parseLong(value.getJSONObject("media").getString("id")));
                comment.setMediaProductType(value.getJSONObject("media").getString("media_product_type"));

                // Send Comment to Publisher
                log.info("-------WEBHOOK COMMENT : "+comment);
                commentPublisher.publishComment(comment);

            } else if(jsonObject.has("messaging")) {
                
                JSONObject value = jsonObject.getJSONArray("messaging").getJSONObject(0);

                // Create Post
                Post post = new Post();
                post.setId(id++);
                post.setSenderId(Long.parseLong(value.getJSONObject("sender").getString("id")));
                post.setRecipientId(Long.parseLong(value.getJSONObject("recipient").getString("id")));
                post.setMessageMid(value.getJSONObject("message").getString("mid"));
                post.setMessageText(value.getJSONObject("message").getString("text"));

                // Send Post to Publisher
                log.info("-------WEBHOOK POST : "+post);
                postPublisher.postPublish(post);
                
            }
        } catch (JSONException e) {
            log.info(e.getMessage());
        }
    }
}
