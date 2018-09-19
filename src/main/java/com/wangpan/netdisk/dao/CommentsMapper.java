package com.wangpan.netdisk.dao;

import com.wangpan.netdisk.model.Comments;
import com.wangpan.netdisk.model.UserInfo;

import java.util.List;

public interface CommentsMapper{
    int deleteByPrimaryKey(Integer msgno);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(String msgno);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
    List<Comments> findComments();
    UserInfo selectUsericon(String namea);
    int selectGoodNum(Comments c);
    UserInfo selectUserInfo(String belongname);
    int updateGrade(UserInfo userInfo);
}