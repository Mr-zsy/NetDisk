package com.wangpan.netdisk.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Files {
    private Integer id;

    private String username;

    private String fileno;

    private String file;

    private Date uploadtime;

    private Integer goodno;

    private String filesize;

    private String filename;

    private String localUploadTime;

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno == null ? null : fileno.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Date getUploadtime() {
        return uploadtime;

    }

    public String getLocalUploadTime() {
        localUploadTime = this.getUploadtime().toLocaleString();
        return localUploadTime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getGoodno() {
        return goodno;
    }

    public void setGoodno(Integer goodno) {
        this.goodno = goodno;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize == null ? null : filesize.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

//    public String getUsericon() {
//        return usericon;
//    }

//    public void setUsericon(String usericon) {
//        this.usericon = usericon == null ? null : usericon.trim();
//    }

}