package com.lemon.profiler.service;


import java.util.List;

import org.json.simple.JSONObject;

import com.lemon.profiler.model.User;

public interface UserService {
    public List<User> getAllUsers();
    public void updateUser(User emp);
    public void deleteUser(User id);
    public User getUser(String id);
    public JSONObject createUser(User emp);
}
