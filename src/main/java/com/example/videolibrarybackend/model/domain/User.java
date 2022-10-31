package com.example.videolibrarybackend.model.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "USER_TABLE")
public class User extends BaseEntity{

    private String userName;

    private String password;

    private String fullName;

}
