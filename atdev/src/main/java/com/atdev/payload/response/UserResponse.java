package com.atdev.payload.response;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Integer userId;

    private String userName;

    private String userMobNo;

    private String userEmail;

    private String address;

    private String gender;

    private Date date;

    private String status;
}
