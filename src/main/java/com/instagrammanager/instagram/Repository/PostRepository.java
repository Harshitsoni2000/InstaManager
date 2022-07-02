package com.instagrammanager.instagram.Repository;

import com.instagrammanager.instagram.Model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
