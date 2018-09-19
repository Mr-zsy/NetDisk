//package com.sys.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.sys.dao.UserDao;
//import com.sys.model.User;
//
//import java.util.Base64.Encoder;
//import java.util.Base64.Decoder;
//@Service
//@Transactional
//public class UserService {
//
//
//    @Autowired
//    private UserDao dao;
//
//    public List<User> findByUsername(String userName) throws Exception {
//        Map<String, Object> map  = new HashMap<String,Object>();
//        map.put("userName", userName);
//
//        List<User> lists = dao.findByUsername(map);
//        return lists;
//    }
//
//    public List<User> findByUsername(String userName, String password) throws Exception {
//        Map<String, Object> map  = new HashMap<String,Object>();
//        map.put("userName", userName);
//        map.put("password",new String(Base64.encodeBase64(password.getBytes())));
//
//        List<User> lists = dao.findByUsername(map);
//        return lists;
//    }
//
//    public void insert(User user) throws Exception {
//        dao.insert(user);
//    }
//
//    public void update(User user) throws Exception {
//        dao.update(user);
//    }
//
//    public void delete(String userlist) throws Exception {
//        dao.delete(userlist);
//    }
//
//    public Map<String, Object> getUserLevel(String userName) throws Exception {
//        Map<String, Object> map = new HashMap<String, Object>();
//        Integer hps = dao.getUserLevel(userName);
//        Integer useSpace = dao.getUserLevel(userName);
//        //String imgpath=dao.getUserImgpath(userName);
//        Integer grade = 0;
//        Integer space = 0;
//        Integer singlefile = 0;
//        if (hps == null) {
//        	grade = 0;
//            space = 0;
//            singlefile = 0;
//        }
//        else if (hps >= 1 && hps < 10) {
//            grade = 1;
//            space = 5;
//            singlefile = 5;
//        } else if (hps >= 10 && hps < 30) {
//            grade = 2;
//            space = 10;
//            singlefile = 50;
//        } else if (hps >= 30 && hps < 80) {
//            grade = 3;
//            space = 50;
//            singlefile = 80;
//        } else if (hps >= 80) {
//            grade = 4;
//            space = 100;
//            singlefile = 100;
//        }
//
//        //map.put("imgpath", imgpath);
//        map.put("userName", userName);
//        map.put("grade", grade);
//        map.put("useSpace", useSpace);
//        map.put("canUseSpace", space);
//        map.put("itemSize", singlefile);
//
//        return map;
//    }
//}
