package com.wangpan.netdisk.service;

import com.wangpan.netdisk.model.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserInfoSer {
    Map login(String userName,String password);
    Map register(String userName, String password, MultipartFile userIcon);
    Map getMessage(String userName);
    String changePassword(String userName,String oldPassword,String newPassword);


    Map selectUser(String userName);
}
