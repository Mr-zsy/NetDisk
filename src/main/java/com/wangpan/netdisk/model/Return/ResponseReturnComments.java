package com.wangpan.netdisk.model.Return;

import com.wangpan.netdisk.model.Comments;

import java.util.Date;

public class ResponseReturnComments {

    private String usernamea;

    private String usernameb;
    private String msg;
    private String msgno;
    private Date time;
    private String userIcon;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsernamea() {
        return usernamea;
    }

    public void setUsernamea(String usernamea) {
        this.usernamea = usernamea;
    }

    public String getUsernameb() {
        return usernameb;
    }

    public void setUsernameb(String usernameb) {
        this.usernameb = usernameb;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgno() {
        return msgno;
    }

    public void setMsgno(String msgno) {
        this.msgno = msgno;
    }
    public void set(ResponseReturnComments r){
        this.setMsg(r.getMsg());
        this.setMsgno(r.getMsgno());
        this.setUsernamea(r.getUsernamea());
        this.setUsernameb(r.getUsernameb());
        this.setTime(r.getTime());
        this.setUserIcon(r.getUserIcon());

    }
    public void setFromComments(Comments c){
        this.setMsg(c.getMsg());
        this.setMsgno(c.getMsgno());
        this.setUsernamea(c.getUsernamea());
        this.setUsernameb(c.getUsernameb());
        this.setTime(c.getCommenttime());

    }
}
