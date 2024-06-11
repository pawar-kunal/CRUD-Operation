package com.atdev.service;

import com.atdev.payload.request.UserRequest;
import com.atdev.payload.response.MainResponse;
import com.atdev.payload.response.UserResponse;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    MainResponse update(UserRequest userRequest);

    UserResponse getUser(Integer userId);

    MainResponse deleteUser(Integer userId);
}
