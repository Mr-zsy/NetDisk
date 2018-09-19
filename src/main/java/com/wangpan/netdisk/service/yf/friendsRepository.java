package com.wangpan.netdisk.service.yf;

import com.wangpan.netdisk.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface friendsRepository extends JpaRepository<Friends, Integer> {
	public java.util.List<Friends> findALLByFriendname(String friendname);
	public java.util.List<Friends> findByUsernameAndFriendname(String username,String friendname);
}
