package com.example.project_a.core.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class LoginVO {
    private String username;
    private String password;
}
