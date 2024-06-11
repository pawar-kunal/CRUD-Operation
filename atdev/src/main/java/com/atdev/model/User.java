package com.atdev.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;

    private String userMobNo;

    private String userEmail;

    private String address;

    private String gender;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String status;
}
