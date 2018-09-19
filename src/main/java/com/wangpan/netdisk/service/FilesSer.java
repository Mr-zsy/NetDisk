package com.wangpan.netdisk.service;

/*文件操作业务逻辑接口类*/

import com.wangpan.netdisk.model.Files;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FilesSer {


    String uploadFile(MultipartFile file,String userName);

    Map selectHotFiles();
    Map selectNewFiles();
    Map selectMyFiles(String userName);
    Files selectFileDetail(String userName,String fileNo);
    Map deleteFile(String fileNo);

    Map saveFilesToMe(String userName,String fileNo);

}


