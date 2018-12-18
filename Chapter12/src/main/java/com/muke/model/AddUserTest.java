package com.muke.model;

import lombok.Data;

@Data
public class AddUserTest {
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;
}
