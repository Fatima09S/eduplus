package com.ipd.eduplus.domain.dtos;

import com.ipd.eduplus.domain.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String fullname;
    private String email;
    private String password;
    private Role role;
}