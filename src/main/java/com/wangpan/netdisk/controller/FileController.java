package com.wangpan.netdisk.controller;

import com.wangpan.netdisk.model.Files;
import com.wangpan.netdisk.service.FilesSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FilesSer filesSer;

//    上传文件
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String file(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userName") String userName){
        return filesSer.uploadFile(file,userName);
    }

//热门文件
    @RequestMapping(value = "/hotFiles",method = RequestMethod.GET)
    public Map hotFile(){
        return filesSer.selectHotFiles();
    }

//最新文件
    @RequestMapping(value = "/newFiles",method = RequestMethod.GET)
    public Map newFiles(){return filesSer.selectNewFiles();}

//我的文件
    @RequestMapping(value = "/myFiles",method = RequestMethod.POST)
    public Map myFiles(
            @RequestParam("userName") String userName
    ){return filesSer.selectMyFiles(userName);}

//    文件详情
    @RequestMapping(value = "/fileDetail",method = RequestMethod.GET)
    public Files fileDetail(
            @RequestParam("fileNo") String fileNo,
            @RequestParam("userName") String userName
    ){return filesSer.selectFileDetail(userName,fileNo);}

//    删除文件
    @RequestMapping(value = "/deleteFile",method = RequestMethod.GET)
    public Map deleteFile(
            @RequestParam("fileNo") String fileNo
    ){return filesSer.deleteFile(fileNo);}

//    保存文件至我的网盘
    @RequestMapping(value = "/saveToMe",method = RequestMethod.POST)
    public Map saveFilesToMe(
            @RequestParam("userName") String userName,
            @RequestParam("fileNo") String fileNo
    ){
        return filesSer.saveFilesToMe(userName,fileNo);
    }
}
