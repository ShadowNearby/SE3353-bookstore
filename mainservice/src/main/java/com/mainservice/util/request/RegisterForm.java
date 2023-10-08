package com.mainservice.util.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
    private String username;
    private String password;
    private String email;
}
