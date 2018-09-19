package com.wangpan.netdisk.service.yf;


import com.wangpan.netdisk.model.yf.user_friend_info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface friend_rela_Repository extends JpaRepository<user_friend_info,String> {
	public java.util.List<user_friend_info> findAllByUsernameOrFriendname(String username,String friendname);
	public java.util.List<user_friend_info> findAllByFriendname(String friendname);
}
