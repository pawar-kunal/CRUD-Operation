package com.atdev.service;

import java.util.List;
import java.util.Map;

public interface ThirdPartyService {

    List<Map<String,Object>> getPosts();

    Map<String,Object> getPostById(Integer id);

    Map<String, Object> addPost(Map<String,Object> payload);
}
