package com.atdev.payload.request;

import lombok.Data;

@Data
public class UserRequest {

    private Integer userId;

    private String userName;

    private String userMobNo;

    private String userEmail;

    private String address;

    private String gender;
}
