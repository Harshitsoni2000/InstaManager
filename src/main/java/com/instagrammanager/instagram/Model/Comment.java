package com.instagrammanager.instagram.Model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Comment implements Serializable{
    
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
