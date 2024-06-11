package com.atdev.controller;

import com.atdev.model.User;
import com.atdev.payload.request.UserRequest;
import com.atdev.payload.response.MainResponse;
import com.atdev.payload.response.UserResponse;
import com.atdev.service.UserService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserRequest userRequest){
        UserResponse userResponse = this.userService.create(userRequest);

        if (userResponse!=null){
            return new ResponseEntity(userResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(userResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody UserRequest userRequest){
        MainResponse mainResponse = this.userService.update(userRequest);
        return new ResponseEntity(mainResponse, HttpStatus.OK);
    }

    @GetMapping("/getuser/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") Integer userId){
        UserResponse userResponse = this.userService.getUser(userId);
        return new ResponseEntity(userResponse,HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Integer userId){
        MainResponse mainResponse = this.userService.deleteUser(userId);
        return new ResponseEntity(mainResponse, HttpStatus.OK);
    }

    @GetMapping("/getallusers")
    public ResponseEntity getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
