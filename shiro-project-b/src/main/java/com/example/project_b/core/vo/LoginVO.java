package com.example.project_b.core.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class LoginVO {
    private String username;
    private String password;
}
