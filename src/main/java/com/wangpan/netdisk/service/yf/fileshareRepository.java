package com.wangpan.netdisk.service.yf;

import com.wangpan.netdisk.model.yf.fileshare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface fileshareRepository extends JpaRepository<fileshare, Integer> {
	public List<fileshare> findByFileno(String fileno);

	public List<fileshare> findByFriendnameAndFileno(String username,String fileno);

	public List<fileshare> findAllByUsernameAndFriendname(String username,String  friendname);
}
