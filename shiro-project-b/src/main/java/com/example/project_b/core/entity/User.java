package com.example.project_b.core.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable{

    private Integer id;

    private String userName;

    private String userPassword;

    private Integer status;

}
