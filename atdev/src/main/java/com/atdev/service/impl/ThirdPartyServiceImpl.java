package com.atdev.service.impl;

import com.atdev.service.ThirdPartyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    private RestTemplate restTemplate;

    String baseUrl = "https://jsonplaceholder.typicode.com/";
    StringBuilder stringBuilder = new StringBuilder(baseUrl);
    String POST = "posts";
    @Override
    public List<Map<String, Object>> getPosts() {

        String url = stringBuilder.append(POST).toString();
        HttpEntity <Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        val response =  restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> getPostById(Integer id) {
        String POST = "posts/";
        String url = stringBuilder.append(POST).append(id).toString();
        HttpEntity <Void> httpEntity = new HttpEntity<>(getHttpHeaders());
        val response =  restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> addPost(Map<String, Object> payload) {
        String url = stringBuilder.append(POST).toString();
        HttpEntity <Map> httpEntity = new HttpEntity<>(payload,getHttpHeaders());
        val response =  restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> updatePost(Map<String, Object> payload, Integer id) {
        String POST = "posts/";
        String url = stringBuilder.append(POST).append(id).toString();
        HttpEntity <Map> httpEntity = new HttpEntity<>(payload,getHttpHeaders());
        val response =  restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
        System.out.println("response "+response);
        return response.getBody();
    }

    @Override
    public Map<String, Object> deleteById(Integer id) {
        String POST = "posts/";
        String url = stringBuilder.append(POST).append(id).toString();
        HttpEntity <Map> httpEntity = new HttpEntity<>(getHttpHeaders());
        val response =  restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
        System.out.println("response "+response);
        return response.getBody();
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
