package com.myproject.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<String> getUsers() {
        return List.of("1", "2", "3");
    }
}
