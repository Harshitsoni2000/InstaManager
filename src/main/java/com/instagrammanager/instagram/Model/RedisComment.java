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
@RedisHash("RedisComment")
public class RedisComment implements Serializable {
    
    @Id
    private Long id;
    @Column(name = "Comment")
    private String text;
    @Column(name = "User_ID")
    private Long fromUserId;
    @Column(name = "User_Name")
    private String fromusername;
    @Column(name = "Media_ID")
    private Long mediaId;
    @Column(name = "Media_Product_Type")
    private String mediaProductType;    
}
