package com.wangpan.netdisk.controller.yf;

import java.util.ArrayList;
import java.util.List;

import com.wangpan.netdisk.model.Friends;
import com.wangpan.netdisk.model.yf.user;
import com.wangpan.netdisk.model.yf.user_friend_info;

import com.wangpan.netdisk.service.yf.friend_rela_Repository;
import com.wangpan.netdisk.service.yf.friendsRepository;
import com.wangpan.netdisk.service.yf.userinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/friend")
public class friendsController {
	@Autowired
	private friendsRepository friendsres;
	@Autowired
	private friend_rela_Repository frirres;
	@Autowired
	private userinfoRepository userfres;
	/**
	 * 传入当前用户名，和要添加的好友名
	 * 返回0时不存在该用户
	 * 返回1时已经时该用户的好友
	 * 返回2时添加好友成功
	 *
	 * @return
	 */
	@PostMapping(value="/addfriends")
	public String addfriend(@RequestParam("username")String username,@RequestParam("friendname")String friendname){
		List<Friends> checkIsExist=new ArrayList<Friends>();
		List<user> checkUserIsExist=new ArrayList<user>();
		Friends afriends=new Friends();
		List<user_friend_info> a= frirres.findAllByUsernameOrFriendname(username, username);
		List<Friends> acpt=new ArrayList<Friends>();
		for(int i=0;i<a.size();i++) {
			Friends b=new Friends();
			if(a.size()>0) {
			if(a.get(i).getUsername().equals(username)) {
			b.setUsername(a.get(i).getUsername());
			b.setFriendname(a.get(i).getFriendname());
			acpt.add(b);}
			else {
				b.setUsername(a.get(i).getFriendname());
				b.setFriendname(a.get(i).getUsername());
				acpt.add(b);
			}}else {

			}
		}
		checkUserIsExist=userfres.findByUsername(friendname);
		boolean first=false;
		boolean second=false;
		if(checkUserIsExist.size()>0) {

		}else {
			first=true;
		}
		if(first==true) {
			return "您添加的用户不存在!";
		}else {
		for (int i=0;i<acpt.size();i++) {
			if(acpt.get(i).getFriendname().equals(friendname)) {
				second=true;
				break;
			}else {

			}}
		if (second==true) {
			return "已经您的好友了";
		}else {
			}
		}
			afriends.setUsername(username);
			afriends.setFriendname(friendname);
			friendsres.save(afriends);
			return "添加成功!!";
}
	/**
	 * 删除好友
	 * @param username
	 * @param friendname
	 * @return
	 */
	@RequestMapping(value = "/deleteFriend",method = RequestMethod.POST)
	public String deletfriend(
	        @RequestParam(value="userName")String username,
            @RequestParam(value="friendName")String friendname) {
		int i=0;
		i=friendsres.findByUsernameAndFriendname(username,friendname).get(0).getId();
		friendsres.deleteById(i);
		return "删除成功";
		}
}
