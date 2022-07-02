package com.instagrammanager.instagram.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post implements Serializable{
    
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
