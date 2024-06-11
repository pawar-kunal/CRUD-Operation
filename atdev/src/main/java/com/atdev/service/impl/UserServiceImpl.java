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
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        user.setStatus("Active");
        user.setDate(new Date());
        try {
             User createdUser= userRepository.save(user);
             BeanUtils.copyProperties(createdUser,userResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userResponse;
    }

    @Override
    public MainResponse update(UserRequest userRequest) {
        System.out.println("re = "+userRequest);
        MainResponse mainResponse = new MainResponse();

        Optional<User> user = this.userRepository.findById(userRequest.getUserId());

        if (user.isPresent()){
            BeanUtils.copyProperties(userRequest,user.get());
            user.get().setDate(new Date());
            try {
                userRepository.save(user.get());
                System.out.println("update");
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
            BeanUtils.copyProperties(user.get(),userResponse);
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

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }
}
