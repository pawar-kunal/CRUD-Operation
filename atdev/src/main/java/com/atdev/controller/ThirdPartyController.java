package com.atdev.controller;

import com.atdev.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/thirdparty")
public class ThirdPartyController {

    @Autowired
    private ThirdPartyService thirdPartyService;

    @GetMapping("/getposts")
    public List<Map<String, Object>> getAllPosts(){
        return thirdPartyService.getPosts();
    }

    @GetMapping("/getpostbyid/{id}")
    public Map<String, Object> getPostById(@PathVariable("id") Integer id){
        return thirdPartyService.getPostById(id);
    }

    @PostMapping("/addpost")
    public Map<String,Object> addPost(@RequestBody Map<String,Object> payload){
        return thirdPartyService.addPost(payload);
    }

    @PutMapping("/updatepost/{id}")
    public Map<String, Object> updatePost(@RequestBody Map<String, Object> payload,@PathVariable("id") Integer id){
        return thirdPartyService.updatePost(payload,id);
    }

    @DeleteMapping("/deletepost/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") Integer id){
        return thirdPartyService.deleteById(id);
    }
}
