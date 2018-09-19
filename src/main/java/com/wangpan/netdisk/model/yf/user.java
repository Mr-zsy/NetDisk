package com.wangpan.netdisk.model.yf;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="usericon")
    private String usericon;
    @Column(name="grade")
    private Integer grade;
    @Column(name="canusespace")
    private String canusespace;
    @Column(name="")
    private String usedspace;
    private String maxuploadsize;
    public user() {

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
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsericom() {
        return usericon;
    }
    public void setUsericom(String usericom) {
        this.usericon = usericom;
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
        this.canusespace = canusespace;
    }
    public String getUsedspace() {
        return usedspace;
    }
    public void setUsedspace(String usedspace) {
        this.usedspace = usedspace;
    }
    public String getMaxuploadsize() {
        return maxuploadsize;
    }
    public void setMaxuploadsize(String maxuploadsize) {
        this.maxuploadsize = maxuploadsize;
    }
}
