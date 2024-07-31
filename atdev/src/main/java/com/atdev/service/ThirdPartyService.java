package com.atdev.service;

import java.util.List;
import java.util.Map;

public interface ThirdPartyService {

    List<Map<String,Object>> getPosts();

    Map<String,Object> getPostById(Integer id);

    Map<String, Object> addPost(Map<String,Object> payload);

    Map<String, Object> updatePost(Map<String, Object> payload, Integer id);

    Map<String,Object> deleteById(Integer id);
}
