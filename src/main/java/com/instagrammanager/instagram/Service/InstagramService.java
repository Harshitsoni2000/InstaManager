package com.instagrammanager.instagram.Service;

import java.util.List;

public interface InstagramService {
    
    // Get Posts
    public List<Object> getAll();
    public Object getById(Long id);

    // Add Posts
    public void addAll(List<Object> list);
    public void add(Object list);

    // Delete Posts
    public void delete(Long id);
}
