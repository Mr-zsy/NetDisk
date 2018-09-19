package com.wangpan.netdisk.model.yf;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="files")
public class file {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String fileno;
    private String file;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String uploadtime;
    private int goodno;
    private String filesize;
    private String filename;
    public file() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFileno() {
        return fileno;
    }
    public void setFileno(String fileno) {
        this.fileno = fileno;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public String getUploadtime() {
        return uploadtime;
    }
    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }
    public int getGoodno() {
        return goodno;
    }
    public void setGoodno(int goodno) {
        this.goodno = goodno;
    }
    public String getFilesize() {
        return filesize;
    }
    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

}

