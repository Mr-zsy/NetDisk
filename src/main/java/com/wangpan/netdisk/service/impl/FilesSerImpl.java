package com.wangpan.netdisk.service.impl;

import com.wangpan.netdisk.dao.FilesMapper;
import com.wangpan.netdisk.dao.UserInfoMapper;
import com.wangpan.netdisk.model.Files;
import com.wangpan.netdisk.model.UserInfo;
import com.wangpan.netdisk.service.FilesSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FilesSerImpl implements FilesSer {

    @Autowired
    private FilesMapper filesMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

//    上传文件
    private static String PREVIEW_FOLDER = "http://localhost:8080/Files/";
    private static String UPLOADED_FOLDER = "/home/zsy/IdeaProjects/netdist/src/main/resources/static/Files/";
    @Transactional
    public String uploadFile(MultipartFile file,String userName){

        if (file.isEmpty()) {
            return "文件为空";
        }
        //文件路径　file字段存储
        File path = new File(UPLOADED_FOLDER + file.getOriginalFilename());
        try{
            //文件大小
            long fileSize = file.getSize();
            //文件名
            String fileName = file.getOriginalFilename();
            //用户名　userName
            System.out.println("userName: "+userName);
            UserInfo user = userInfoMapper.selectByUserName(userName);
            Integer maxId = 0;
//            获取最大上传限制大小　　单位ｂｙｔｅ（Ｂ
            if(filesMapper.selectMaxId() != null){
                 maxId = filesMapper.selectMaxId();
            }
            if( Double.parseDouble(user.getMaxuploadsize()) >= fileSize){
                Files addFile = new Files();
                addFile.setUsername(userName);
                addFile.setFileno(new Date().getTime()+ ""+maxId+1+"" );
                addFile.setFile(PREVIEW_FOLDER + file.getOriginalFilename());
                addFile.setFilesize(Long.toString(fileSize));
                addFile.setFilename(fileName);

                filesMapper.insertSelective(addFile);
                file.transferTo(path);
                return "上传成功";
            }else{
                return "超过您的文件最大上传限制";
            }

        }catch (IllegalStateException e) {
            e.printStackTrace();
            return "上传失败";
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }

    }

//    热门文件
    public Map selectHotFiles(){
        Map map = new HashMap();
        List<Files> hotFiles = filesMapper.selectHotFiles();
        map.put("hotFilesList",hotFiles);
        return map;
    }

//  最新文件
    public Map selectNewFiles(){
        Map map = new HashMap();
        List<Files> newFiles = filesMapper.selectNewFiles();
        map.put("newFilesList",newFiles);
        return map;
    }

//    我的文件
    public Map selectMyFiles(String userName){
        Map map = new HashMap();
        List<Files> myFiles = filesMapper.selectMyFiles(userName);
        map.put("myFilesList",myFiles);
        return map;
    }

//文件详情

    public Files selectFileDetail(String userName,String fileNo){


        Files file1 = new Files();
        file1.setUsername(userName);
        file1.setFileno(fileNo);
        Files isFiles = filesMapper.selectByFileNoAndUserName(file1);

        return isFiles;
    }

//    删除文件
    public Map deleteFile(String fileNo){
        Map map = new HashMap();
        try {
            filesMapper.deleteByFileNo(fileNo);
            map.put("message","success");

        }catch (Exception e){
            map.put("message","error");
        }
        return map;
    }



//    ***
        public Map saveFilesToMe(String userName,String fileNo){
            Map map = new HashMap();
//        try {
            Files files = filesMapper.selectByFileNo(fileNo);
            Files file1 = new Files();
            file1.setUsername(userName);
            file1.setFileno(fileNo);
            Files isFiles = filesMapper.selectByFileNoAndUserName(file1);
            if(isFiles == null){
                Files saveFile = new Files();
                saveFile.setUsername(userName);
                saveFile.setFileno(files.getFileno());
                saveFile.setFilesize(files.getFilesize());
                saveFile.setFilename(files.getFilename());
                saveFile.setFile(files.getFile());
//                files.setUsername(userName);
                filesMapper.insertSelective(saveFile);
                map.put("message","保存成功!");
            }else{
                map.put("message","该文件已存在!");
            }

//        }catch (Exception e){
//            map.put("message","服务器故障,保存失败!");
//        }
            return map;

        }
    }

