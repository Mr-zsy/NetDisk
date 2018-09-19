package com.wangpan.netdisk.model.Return;

import com.wangpan.netdisk.model.Comments;
import javafx.print.Collation;

import java.util.Collections;

public class ReturnComments {
    private String usernamea;
    private String msg;
    private String msgno;
    private ResponseReturnComments[] responseReturnComments;
    private String userIcon;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUsernamea() {
        return usernamea;
    }
    public void setUsernamea(String usernamea) {
        this.usernamea = usernamea;
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

    public ResponseReturnComments[] getResponseReturnComments() {
        return responseReturnComments;
    }

    public void setResponseReturnComments(ResponseReturnComments[] responseReturnComments) {
        int i = 0;
        this.responseReturnComments = new ResponseReturnComments[responseReturnComments.length];
        System.out.println("length:"+responseReturnComments.length);
        for (ResponseReturnComments response :responseReturnComments
             ) {
            this.responseReturnComments[i] = new ResponseReturnComments();
            System.out.println("namea:"+responseReturnComments[i].getUsernamea());
            System.out.println("thisresponse:"+ this.responseReturnComments[i]);
            if (responseReturnComments[i].getUsernamea()!=null) {

                this.responseReturnComments[i].set(responseReturnComments[i]);
                i++;
                System.out.println("ccccccccccccccccccs");
                System.out.println("i:" + i);
            }
        }

    }
    public void set(ReturnComments[] returnComments){
        for(int i = 0;i<returnComments.length;i++){
            this.msg = returnComments[i].getMsg();
            this.msgno = returnComments[i].getMsgno();
            this.usernamea = returnComments[i].getUsernamea();
            this.setResponseReturnComments(returnComments[i].getResponseReturnComments());


        }

    }
    public void setFromComments(Comments comments){
        this.setMsg(comments.getMsg());
        this.setMsgno(comments.getMsgno());
        this.setUsernamea(comments.getUsernamea());
    }
    public void setEmptyRespon(){
        this.responseReturnComments = new ResponseReturnComments[0];
    }









}
