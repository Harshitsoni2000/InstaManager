package com.instagrammanager.instagram.Repository;

import com.instagrammanager.instagram.Model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
