package com.example.user.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class LoginVO {
    private String username;
    private String password;
}
