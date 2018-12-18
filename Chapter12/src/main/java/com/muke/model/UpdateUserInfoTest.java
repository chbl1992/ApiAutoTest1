package com.muke.model;

import lombok.Data;

@Data
public class UpdateUserInfoTest {
    private int id;
    private int userId;
    private String userName;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;
}
