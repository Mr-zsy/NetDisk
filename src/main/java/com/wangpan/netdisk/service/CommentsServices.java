package com.wangpan.netdisk.service;


import com.wangpan.netdisk.dao.CommentsMapper;
import com.wangpan.netdisk.dao.UserInfoMapper;
import com.wangpan.netdisk.model.Comments;
import com.wangpan.netdisk.model.Return.ResponseReturnComments;
import com.wangpan.netdisk.model.Return.ReturnComments;
import com.wangpan.netdisk.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

@Service(value = "services5")
@Transactional
public class CommentsServices {

    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
//    public List<Comments> findComments() {
//        List<Comments> list = commentsMapper.findComments();
//        return list;
//    }

    //根据时间排序
    public Void sort(ResponseReturnComments[] responseReturnComments){
        ResponseReturnComments c = new ResponseReturnComments();
        for(int j = 1;j<responseReturnComments.length;j++){
            if (responseReturnComments[j].getTime()!=null){
                if (responseReturnComments[j].getTime().compareTo(responseReturnComments[j-1].getTime())<0){
                    c.set(responseReturnComments[j]);
                    responseReturnComments[j].set(responseReturnComments[j-1]);
                    responseReturnComments [j-1].set(c);
                }
            }
        }
        return null;
    }
    //返回前端所需类ReturnComments
    public ReturnComments[] returnComments(Comments[][] comments ,String fileno){
//        System.out.println("find:"+comments[0][0].getUsernamea());

        ReturnComments[] returnComments = new ReturnComments[comments.length];
        ResponseReturnComments[] responseReturnComments;
        System.out.println(fileno);
        for (int i = 0;i<comments.length;i++){
            returnComments[i] = new ReturnComments();

        }

        for(int i = 0;i<comments.length;i++){
            System.out.println("com.length:---------------------"+comments[i].length);
            int length = comments[i].length;
            if (comments[i].length >1){
                length--;
            }

            responseReturnComments = new ResponseReturnComments[length];

            for (int j =0;j<comments[i].length;j++){
                if (fileno.equals(comments[i][j].getFileno())&&(comments[i][j].getUsernameb() == null)
                &&comments[i][j].getUsernamea()!=null){
                    returnComments[i].setUsernamea(comments[i][j].getUsernamea());
                    returnComments[i].setMsgno(comments[i][j].getMsgno());
                    returnComments[i].setMsg(comments[i][j].getMsg());
                    UserInfo userInfo = commentsMapper.selectUsericon(comments[i][j].getUsernamea());
                    System.out.println("icon:"+userInfo.getUsericon());
                    returnComments[i].setUserIcon(userInfo.getUsericon());
                    System.out.println("usericon:"+returnComments[i].getUserIcon());
//                    returnComments[l].setUserIcon((commentsMapper.selectUsericon(comments[i][j].getUsernamea())).getUsericon());
                    System.out.println("find:"+comments[i][j].getUsernamea());
                    System.out.println("length:"+comments[i].length);
                    if (comments[i].length>1){
                    //对responseReturnComments进行不同长度的初始化
                        for (int k =0;k<comments[i].length-1;k++){
                            responseReturnComments[k]=new ResponseReturnComments();
                        }
                        for(int k=0,z=0;k<comments[i].length;k++){
                            if ((comments[i][k].getUsernamea() != null)
                                    && (comments[i][k].getUsernameb() != null)
                            ) {
                                responseReturnComments[z].setFromComments(comments[i][k]);
                                UserInfo userInfo1 =commentsMapper.selectUsericon(comments[i][k].getUsernamea());
                                responseReturnComments[z].setUserIcon(userInfo1.getUsericon());
    //                            responseReturnComments[z].setUserIcon((commentsMapper.selectUsericon(comments[i][k].getUsernamea())).getUsericon());
                                System.out.println("comm:"+comments[i][k].getId());
                                System.out.println("usericon:"+responseReturnComments[z].getUserIcon());
                                z++;
                            }
                        }
                        sort(responseReturnComments);
                        returnComments[i].setResponseReturnComments(responseReturnComments);
                    }
                    else{
                        returnComments[i].setEmptyRespon();
                    }

                }
//                l++;
            }
        }
        return returnComments;
    }
    public List<ReturnComments> findComments(String fileNo){
        List<Comments> list = commentsMapper.findComments();
        int id,ib;
        int[] ia = new int[list.size()];
        int[] z = new int[list.size()];

        String[] fileno = new String[list.size()];
        //存储所有评论人
        String[] na = new String[list.size()];
        //存储第一评论人和回复评论人的所属评论号
        String[] rn = new String[list.size()];
        id = 1;
        fileno[0] = "5";
        fileno[1] = "6";
        fileno[2] = "7";
        for(int i = 0;i<list.size();i++){
            ia[i] = 0;
            z[i] = 0;
            na[i] = new String();
            rn[i] = new String();
        }
//        na[0] = list.get(0).getUsernamea();
        //
        Set<String> set = new HashSet<String>();
        for (int i = 0;i<list.size();i++){
            set.add(list.get(i).getUsernamea().toString());
//            System.out.println(list.get(i).getUsernamea());
        }

        int index = 0;

        for (String s:
             set) {na[index] = s;
             index++;
//            System.out.println(s);
        }
        //
        set = new HashSet<String>();
        for (int i = 0;i<list.size();i++){
            if (list.get(i).getFileno().equals(fileNo)) {
                if (list.get(i).getResponno() != null) {
                    set.add(list.get(i).getResponno().toString());
                    System.out.println(list.get(i).getResponno());
                }
                if (list.get(i).getResponno() == null) {
                    set.add(list.get(i).getMsgno().toString());
                    System.out.println(list.get(i).getMsgno());
                }
            }
        }
        index = 0;
        for (String s:
                set) {rn[index] = s;
            index++;
            System.out.println("responno:"+s);
        }

//        for(int i = 0,j=0,l=0;i<8;i++){
////            System.out.println(list.get(i).getUsernamea());
//
//            for (int k = 0;k<=i;k++){
//                if (na[k].equals(list.get(i).getUsernamea())){
//                    break;
//                }
//                else {
//                    j++;
//                    na[j] = list.get(i).getUsernamea();
//                    System.out.println("j="+j);
//                    System.out.println(list.get(i).getUsernamea());
//                    System.out.println("66666");
//                }
//
//            }

//            System.out.println(na[i]);
//        }
        System.out.println(set);
        System.out.println("123456");
        System.out.println(rn[0]);
        System.out.println(rn[1]);
        System.out.println(rn[2]);
        ib = 0;
        Comments[][] namea = new Comments[list.size()][list.size()];
        //实例化namea
        for (int i = 0;i<list.size();i++){
            for (int j = 0;j<list.size();j++){
                namea[i][j] = new Comments();
            }
        }
        //na存储对应的usernamea
        System.out.println("635241");
        for (int k = 0,l=0,i=0;k<list.size();l++,k++){

//                System.out.println("j:"+j);
                for (int j = 0; j < list.size(); j++) {
                        //取出文件名和用户名a一致数据
                        if ((list.get(j).getFileno().equals(fileNo))
//                            && list.get(j).getUsernamea().equals(na[l])
                                && (rn[k].equals(list.get(j).getResponno()) || rn[k].equals((list.get(j).getMsgno())))
                        ) {
                            namea[k][z[l]].set(list.get(j));
                            z[l]++;
                        }
//                        if (rn[k].equals(list.get(j).getResponno())){
//                            i++;
//                        }
                }
        }

//        System.out.println("cccccccccc");
        //交换namea至合适长度的数组
        //length为记录每个数组的长度

        int length = 0;
        Comments[][] nameaExchange = new Comments[namea.length][];
        for (int i = 0;i<namea.length;i++){
            for (int j = 0;j<namea.length;j++){
                if (namea[i][j].getId()!=null){
                    length++;
                }
            }
            nameaExchange[i]= new Comments[length];
//            System.out.println("namea[]length:"+i+":"+length);
            for (int j = 0;j<length;j++){
                nameaExchange[i][j] = new Comments();
                if (namea[i][j].getId()!=null) {
//                    System.out.println("nameaid:"+namea[i][j].getId());
                    nameaExchange[i][j].set(namea[i][j]);
//                    System.out.println("nameaExchangeid:"+nameaExchange[i][j].getId());
//                    System.out.println("i:"+i);
//                    System.out.println("j:"+j);
                }
            }
            length = 0;
        }

        for (int i = 0;i<nameaExchange.length;i++){

            for (int j =0;j<nameaExchange[i].length;j++) {
                if (nameaExchange[i][j].getId()!=null) {
                    System.out.println("i:"+i+"....j:"+j);
                    System.out.println("namea1:" + nameaExchange[i][j].getId());
                    System.out.println("namea2:" + nameaExchange[i][j].getId());
                    System.out.println("namea3:" + nameaExchange[i][j].getId());
                    System.out.println("namea:"+nameaExchange[i][j].getUsernamea());
                    System.out.println("next-------------------------------");
                }
            }
        }
//        System.out.println(list.get(7).getId());
//        System.out.println(list.get(8).getId());
//        System.out.println(list.get(9).getId());
//        list.remove(7);
//        list.remove(7);
//        list.remove(7);
        //将处理结果赋值给返回对象
        ReturnComments[] returnComments = new ReturnComments[nameaExchange.length];
//        System.out.println("bug1:"+namea[0][0].getUsernamea());
//        System.out.println("bug1:"+namea[1][0].getUsernamea());
//        System.out.println("bug1:"+namea[2][0].getUsernamea());
        returnComments = returnComments(nameaExchange,fileNo);

        List<ReturnComments> list1 = new ArrayList<>();
        for (int i = 0;i<returnComments.length;i++){
//            if (returnComments[i].getResponseReturnComments() ==null){
//                returnComments[i].setEmptyRespon();
//            }
            System.out.println("return:"+returnComments[i].getUsernamea());
            if (returnComments[i].getUsernamea() != null) {
                list1.add(returnComments[i]);
            }

        }
//        System.out.println("return:"+list1.get(0).getUsernamea());

        //k用作循环记录表rn，i,j用来循环namea
//        for (int k = 0;k<10;k++) {
//            for (int i = 0,; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    if (namea[i][j].getUsernamea() != null) {
//
////                    System.out.println("i:"+i+"       j:"+j+"        k:"+k);
////                    System.out.println(namea[i][j].getUsernamea());
////                    list.set(k,namea[i][j]);
////                    k++;
//                        if (rn[k].equals(namea[i][j].getMsgno()) || rn[i].equals(namea[i][j].getResponno())) {
//                            if (namea[i][j].getUsernameb() == null) {
//                                returnComments[k].setFromComments(namea[i][j]);
//                            }
//                            else {
//                                returnComments[k].
//
//
//                            }
//                            }
//                            k++;
//
//                        }
//
//
//                    }
////                System.out.println(namea[i][j].getUsernamea());
//                }
//            }
//        }
        Comments c = list.get(0);
//        System.out.println(list.get(0).getUsernamea());
//        int j;
//        Comments c1 = new Comments();
//        c1.setBelongname("a");
//        c1.setMsgsatus(1);
//        j = commentsMapper.selectGoodNum(c1);
//        String a = "a";
//        UserInfo userInfo = commentsMapper.selectUserInfo(a);
//
//        System.out.println("123456");
//        System.out.println("grade:"+userInfo.getGrade());
//        System.out.println("j:"+j);
//        j = 85;
//
//        int grade = userInfo.getGrade();
//        grade = 1;
//        switch (grade) {
//            case 1: {
//                if (j >= 10) {
//                    userInfo.setCanusespace("10737418240");
//                    userInfo.setMaxuploadsize("157286400");
//                    userInfo.setGrade(2);
//                }
//            }
//            case 2: {
//                if (j >= 30) {
//                    userInfo.setCanusespace("53687091200");
//                    userInfo.setMaxuploadsize("419430400");
//                    userInfo.setGrade(3);
//                }
//            }
//            case 3: {
//                if (j>=80){
//                    userInfo.setCanusespace("107374182400");
//                    userInfo.setMaxuploadsize("1073741824");
//                    userInfo.setGrade(4);
//                }
//
//            }
//
//        }
//        commentsMapper.updateGrade(userInfo);

        return list1;

    }
    public List<ReturnComments> showReturnComments(){
        ReturnComments returnComments = new ReturnComments();
        ResponseReturnComments[] responseReturnComments = new ResponseReturnComments[3];
        for(int i = 0;i<3;i++){
            responseReturnComments[i] = new ResponseReturnComments();
            responseReturnComments[i].setUsernameb("a");
            responseReturnComments[i].setUsernamea("b");
            responseReturnComments[i].setMsgno("i+1");
            responseReturnComments[i].setMsg("hello");
            System.out.println("usernamea:"+responseReturnComments[i].getUsernamea());

        }
        System.out.println("usernamea:"+responseReturnComments[0].getUsernamea());
        returnComments.setResponseReturnComments(responseReturnComments);
        for (int i =0;i<3;i++){
            System.out.println("a:"+returnComments.getResponseReturnComments()[i].getMsgno());
        }

        returnComments.setMsg("hi");
        returnComments.setMsgno("5");
        returnComments.setUsernamea("a");
        List<ReturnComments> list = new ArrayList<>();

        System.out.println("return:"+returnComments.getUsernamea());

        list.add(returnComments);

        return list;
    }




    public Comments insertComments(String msg,String fileno,int msgstatus,
                                   String usernamea,String usernameab,String responno,String belongname) {
        Comments comments = new Comments();
        List<Comments> list = commentsMapper.findComments();
        int id = list.get(list.size() - 1).getId() + 1;
        String msgno = new Date().getTime() + "" + id + "";
//        Comments c = new Comments();
//        c.setBelongname(belongname);
//        c.setMsgsatus(1);
//        UserInfo userInfo = commentsMapper.selectUserInfo(belongname);
//        commentsMapper.updateGrade(userInfo);
//        int goodNum = commentsMapper.selectGoodNum(c);
//        int grade = userInfo.getGrade();
//        switch (grade) {
//            case 1: {
//                if (goodNum >= 10) {
//                    userInfo.setCanusespace("10737418240");
//                    userInfo.setMaxuploadsize("157286400");
//                    userInfo.setGrade(2);
//                }
//            }
//            case 2: {
//                if (goodNum >= 30) {
//                    userInfo.setCanusespace("53687091200");
//                    userInfo.setMaxuploadsize("419430400");
//                    userInfo.setGrade(3);
//                }
//            }
//            case 3: {
//                if (goodNum>=80){
//                    userInfo.setCanusespace("107374182400");
//                    userInfo.setMaxuploadsize("1073741824");
//                    userInfo.setGrade(4);
//                }
//
//            }
//
//        }

        comments.setMsgno(msgno);
        comments.setMsg(msg);
        comments.setFileno(fileno);
        comments.setMsgsatus(msgstatus);
        comments.setUsernamea(usernamea);
        comments.setUsernameb(usernameab);
        comments.setResponno(responno);
        comments.setBelongname(belongname);
        commentsMapper.insert(comments);
        return comments;
    }
}
