package com.wangpan.netdisk.controller.yf;

import java.util.List;

import com.wangpan.netdisk.service.yf.friend_rela_Repository;
import com.wangpan.netdisk.model.yf.user_friend_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/friend")
public class friend_rela_Controller {
	@Autowired
	public friend_rela_Repository frires;
	/**
	 * 查询好友列表
	 * @param username
	 * @return result
	 */
	@PostMapping("/friendselect")
	public List<user_friend_info> select_friend(
			@RequestParam(value="username",required=true)String username){
		friend_rela_Repository friress=frires;
		java.util.List<user_friend_info> aList=new java.util.ArrayList<user_friend_info>();
		java.util.List<user_friend_info> result=new java.util.ArrayList<user_friend_info>();
		java.util.List<user_friend_info> bList=new java.util.ArrayList<user_friend_info>();
		String friendname=new String(username);
		aList= friress.findAllByUsernameOrFriendname(username, friendname);
		bList.addAll(aList);
		for (/**int i=0 ; i<aList.size();i++*/int i=(aList.size()-1); i>=0;i--){
				user_friend_info a=new user_friend_info();
				user_friend_info b=aList.get(i);
				{
				if(username.equals(b.getUsername()))
				{
				a.setUsername(b.getUsername());
				a.setFriendname(b.getFriendname());
				a.setFriend_icom(b.getFriend_icom());
				a.setFriend_grade(b.getFriend_grade());
				a.setUser_icom(null);
				a.setUser_grade(null);
				}else {
					a.setUsername(b.getFriendname());
					a.setFriendname(b.getUsername());
					a.setFriend_icom(b.getUser_icom());
					a.setFriend_grade(b.getUser_grade());
					a.setUser_icom(null);
					a.setUser_grade(null);
					}
				result.add(a);
				}
		}
			return result;
}

	/**
	 * 搜索好友
	 */
//	@GetMapping("/searchfriend")
//	public List<user_friend_info> searchfriend(@RequestParam(value="username")String username,@RequestParam(value="friendname")String friendname) {
//		List<user_friend_info> send=frires.findAllByFriendname(friendname);
//		return send;
//	}
}
