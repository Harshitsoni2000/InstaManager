package com.instagrammanager.instagram.Service;

import java.util.List;

public interface RedisInstagramService {

    // Get Object
    public List<Object> getAll();
    public Object getById(Long id);

    // Add Object
    public void add(Object object);

    // Delete Object
    public void delete(Long id);
}
