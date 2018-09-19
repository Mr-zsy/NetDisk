package com.wangpan.netdisk.controller;

import com.wangpan.netdisk.model.Comments;
import com.wangpan.netdisk.model.Return.ReturnComments;
import com.wangpan.netdisk.model.User;
import com.wangpan.netdisk.service.CommentsServices;
import com.wangpan.netdisk.service.InsertServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class TestController {
//    zidongzhuru




//    @RequestMapping("/if")
//    public Files insertFile(){
//        Files files = insertServices.insertFiles();
//        return null;
//    }
    @Autowired
    CommentsServices commentsServices;
    @RequestMapping(value = "/fc",method = RequestMethod.POST)
    public List<ReturnComments> findCom(
            @RequestParam("fileNo") String fileno
    ){
        System.out.println("controller");
//        String fileno = "153674398842811";
//        List<Comments> list= commentsServices.findComments();
        List<ReturnComments> list= commentsServices.findComments(fileno);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/ic",method = RequestMethod.POST)
    public Comments insertCom(
            @RequestParam("userNamea") String usernamea,
            @RequestParam(value = "userNameb",required = false) String usernameab,
            @RequestParam("fileNo") String fileNo,
            @RequestParam("message") String message,
            @RequestParam(value = "responseMsgNo",required = false) String respon,
            @RequestParam("submitStatus") int status,
            @RequestParam("belongName") String belongname
    ){
        System.out.println("controller");
        Comments comments = commentsServices.insertComments(message,fileNo,status,usernamea,usernameab,respon,belongname);
        return null;
    }
    @RequestMapping("/sr")
    public List<ReturnComments> showReturn(){
        List<ReturnComments> list = commentsServices.showReturnComments();
        return list;
    }

//    @RequestMapping("/fc")
//
//    public List<Comments> findComm(){
//        List<Comments> list = checkServices.findComments();
//        System.out.println(list);
//        return list;
//
//    }
//
//    @RequestMapping("/fu")
//
//    public List<UserInfo> findUi(){
//        List<UserInfo> list = checkServices.findUserInfo();
//        System.out.println(list);
//        return list;
//
//    }
}

