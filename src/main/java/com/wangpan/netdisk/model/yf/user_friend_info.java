package com.wangpan.netdisk.model.yf;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;


@Entity
@IdClass(primary.class)
public class user_friend_info implements Serializable {
	@Id
	private String username;
	private String user_icom;
	private String user_grade;

	@Id
	private String friendname;
	private String friend_icom;
	private String friend_grade;
	public user_friend_info(){
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriendname() {
		return friendname;
	}
	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}
	public String getUser_icom() {
		return user_icom;
	}
	public void setUser_icom(String user_icom) {
		this.user_icom = user_icom;
	}
	public String getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}
	public String getFriend_icom() {
		return friend_icom;
	}
	public void setFriend_icom(String friend_icom) {
		this.friend_icom = friend_icom;
	}
	public String getFriend_grade() {
		return friend_grade;
	}
	public void setFriend_grade(String friend_grade) {
		this.friend_grade = friend_grade;
	};

}
