package com.wangpan.netdisk.model;

import java.util.Date;

public class Comments implements Comparable<Comments>{
    private String msgno;

    private Integer id;

    private String fileno;

    private Date commenttime;

    private String usernamea;

    private String usernameb;

    private String msg;

    private Integer msgsatus;
    private String responno;
    private String belongname;

    public String getBelongname() {
        return belongname;
    }

    public void setBelongname(String belongname) {
        this.belongname = belongname;
    }

    public String getResponno() {
        return responno;
    }

    public void setResponno(String responno) {
        this.responno = responno;
    }

    public String getMsgno() {
        return msgno;
    }

    public void setMsgno(String msgno) {
        this.msgno = msgno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno == null ? null : fileno.trim();
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public String getUsernamea() {
        return usernamea;
    }

    public void setUsernamea(String usernamea) {
        this.usernamea = usernamea == null ? null : usernamea.trim();
    }

    public String getUsernameb() {
        return usernameb;
    }

    public void setUsernameb(String usernameb) {
        this.usernameb = usernameb == null ? null : usernameb.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Integer getMsgsatus() {
        return msgsatus;
    }

    public void setMsgsatus(Integer msgsatus) {
        this.msgsatus = msgsatus;
    }
    //
    @Override
    public int compareTo(Comments comments){
        return this.commenttime.compareTo(comments.getCommenttime());
    }
    public void set(Comments c){
        this.setResponno(c.getResponno());
        this.setCommenttime(c.getCommenttime());
        this.setUsernameb(c.getUsernameb());
        this.setUsernamea(c.getUsernamea());
        this.setMsgsatus(c.getMsgsatus());
        this.setFileno(c.getFileno());
        this.setId(c.getId());
        this.setMsg(c.getMsg());
        this.setMsgno(c.getMsgno());
        this.setBelongname(c.getBelongname());
    }
}