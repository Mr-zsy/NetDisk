package com.wangpan.netdisk.dao;

import com.wangpan.netdisk.model.Files;

import java.util.List;

public interface FilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Files record);

    int insertSelective(Files record);

    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);

//    自定义
    Integer selectMaxId();
    List<Files> selectHotFiles();
    List<Files> selectNewFiles();
    List<Files> selectMyFiles(String userName);
    Files selectByFileNo(String fileNo);
    int deleteByFileNo(String fileNo);


    Files selectByFileNoAndUserName(Files files);
}