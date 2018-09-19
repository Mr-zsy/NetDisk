package com.wangpan.netdisk.service.impl;

import com.wangpan.netdisk.dao.UserInfoMapper;
import com.wangpan.netdisk.model.UserInfo;
import com.wangpan.netdisk.service.UserInfoSer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoSerImpl implements UserInfoSer {
    @Autowired
    private UserInfoMapper userInfoMapper;



    private static String PREVIEW_FOLDER = "http://localhost:8080/userIcons/";
    private static String UPLOAD_FOLDER = "/home/zsy/IdeaProjects/netdist/src/main/resources/static/userIcons/";
//    登录
    public Map login(String userName,String password){
        Map map =new HashMap<String,String>();

        UserInfo user = userInfoMapper.selectByUserName(userName);
        if(user == null){
            map.put("message","用户名不存在!");
        }else {
            //        解密
            String sPassword = new String(Base64.decodeBase64(user.getPassword()));

            if(password.equals(sPassword)){

                map.put("message","success");
            }else{
                System.out.println("fuck!");
                map.put("message","error");
            }
        }

        return map;
    }

//    注册
    public Map register(String userName, String password, MultipartFile userIcon){

        Map map =new HashMap<String,String>();
        if(userInfoMapper.selectByUserName(userName) == null){
            try{
                if(userIcon == null){
                    map.put("message","请选择默认头像");
                }else{
                    //头像路径　file字段存储
                    //File preciewPath = new File(PREVIEW_FOLDER + userIcon.getOriginalFilename());
                    File uploadPath = new File(UPLOAD_FOLDER+ userIcon.getOriginalFilename());
                    userIcon.transferTo(uploadPath);

                    UserInfo user = new UserInfo();
                    user.setUsername(userName);
                    user.setPassword(new String(Base64.encodeBase64(password.getBytes())));
                    //        url编码
                    System.out.println(PREVIEW_FOLDER + URLEncoder.encode( userIcon.getOriginalFilename(),"utf-8"));
                    user.setUsericon(PREVIEW_FOLDER + URLEncoder.encode( userIcon.getOriginalFilename(),"utf-8"));
                    userInfoMapper.insertSelective(user);

                    map.put("message","注册成功！");
                }

            }catch (IllegalStateException e) {
                e.printStackTrace();
                map.put("message","注册失败1！");
            } catch (IOException e) {
                e.printStackTrace();
                map.put("message","注册失败2！");
            }
        }else {
            map.put("message","该用户名已注册～");
        }
        return map;
    }

//    用户信息
    public Map getMessage(String userName){
        Map userMessage = new HashMap();
        UserInfo user = userInfoMapper.selectByUserName(userName);
//        user.setUsericon(user.getUsericon());
        userMessage.put("data",user);
        return userMessage;
    }

//    修改密码
    public String changePassword(String userName,String oldPassword,String newPassword){
        UserInfo user = userInfoMapper.selectByUserName(userName);
        //        解密
        String sPassword = new String(Base64.decodeBase64(user.getPassword()));
        System.out.println("数据库中密码:"+sPassword);
        if(sPassword.equals(oldPassword)){
            user.setPassword(new String(Base64.encodeBase64(newPassword.getBytes())));
            userInfoMapper.updateByPrimaryKeySelective(user);
            return "修改成功~";
        }else{
            return "您输入的密码不正确!";
        }

    }


    public Map selectUser(String userName){
        UserInfo user = userInfoMapper.selectByUserName(userName);
        Map map = new HashMap();
        map.put("user",user);
        return map;
    }
}
