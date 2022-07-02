package com.instagrammanager.instagram.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("RedisPost")
public class RedisPost implements Serializable {
    
    @Id
    private Long id;
    @Column(name = "Sender_ID")
    private Long senderId;
    @Column(name = "Receiver_ID")
    private Long recipientId;
    @Column(name = "Message_Mid")
    private String messageMid;
    @Column(name = "Message_Text")
    private String messageText;
}
