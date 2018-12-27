package com.example.service;

import com.example.model.Auth;

public interface AuthService {

    public Auth getAuthByUserId(String userId) throws Exception;
}
