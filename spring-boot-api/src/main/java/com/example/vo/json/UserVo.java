package com.example.vo.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable{

    private String userId;
    private String password;

    public UserVo(String userId,String password){
        this.userId = userId;
        this.password = password;
    }


}
