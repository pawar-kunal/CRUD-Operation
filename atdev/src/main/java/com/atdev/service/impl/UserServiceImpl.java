package com.atdev.service.impl;

import com.atdev.model.User;
import com.atdev.payload.request.UserRequest;
import com.atdev.payload.response.MainResponse;
import com.atdev.payload.response.UserResponse;
import com.atdev.repository.UserRepository;
import com.atdev.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        User user = new User();
        BeanUtils.copyProperties(user,userRequest);
        user.setStatus("Active");
        user.setDate(new Date());
        try {
             User createdUser= userRepository.save(user);
             BeanUtils.copyProperties(userResponse,createdUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userResponse;
    }

    @Override
    public MainResponse update(UserRequest userRequest) {
        MainResponse mainResponse = new MainResponse();

        Optional<User> user = this.userRepository.findById(userRequest.getUserId());

        if (user.isPresent()){
            BeanUtils.copyProperties(user,userRequest);
            user.get().setDate(new Date());
            user.get().setStatus("Active");
            try {
                userRepository.save(user.get());
                mainResponse.setMessage("User updated successfully");
                mainResponse.setFlag(true);
                mainResponse.setResponseCode(HttpStatus.OK.value());
            }catch (Exception e){
                e.printStackTrace();
                mainResponse.setMessage("User not updated. Something went wrong.");
                mainResponse.setFlag(false);
                mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            }
        }else {
            mainResponse.setMessage("User not found");
            mainResponse.setFlag(false);
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        }
        return mainResponse;
    }

    @Override
    public UserResponse getUser(Integer userId) {
        UserResponse userResponse = new UserResponse();
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()){
            BeanUtils.copyProperties(userResponse,user);
        }
        return userResponse;
    }

    @Override
    public MainResponse deleteUser(Integer userId) {
        MainResponse mainResponse = new MainResponse();
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()){
            try {
                this.userRepository.deleteById(userId);
                mainResponse.setMessage("User deleted successfully");
                mainResponse.setFlag(true);
                mainResponse.setResponseCode(HttpStatus.OK.value());
            }catch (Exception e){
                e.printStackTrace();
                mainResponse.setMessage("User not delete. Something went wrong");
                mainResponse.setFlag(false);
                mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
            }
        }else {
            mainResponse.setMessage("User not found");
            mainResponse.setFlag(false);
            mainResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());
        }
        return mainResponse;
    }
}
