package com.wangpan.netdisk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private String usericon;

    private Integer grade;

    private String canusespace;

    private String usedspace;

    private String maxuploadsize;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon == null ? null : usericon.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCanusespace() {
        return canusespace;
    }

    public void setCanusespace(String canusespace) {
        this.canusespace = canusespace == null ? null : canusespace.trim();
    }

    public String getUsedspace() {
        return usedspace;
    }

    public void setUsedspace(String usedspace) {
        this.usedspace = usedspace == null ? null : usedspace.trim();
    }

    public String getMaxuploadsize() {
        return maxuploadsize;
    }

    public void setMaxuploadsize(String maxuploadsize) {
        this.maxuploadsize = maxuploadsize == null ? null : maxuploadsize.trim();
    }
}