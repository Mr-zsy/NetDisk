package com.wangpan.netdisk.service.yf;


import java.util.List;

import com.wangpan.netdisk.model.yf.user;
import org.springframework.data.jpa.repository.JpaRepository;


public interface userinfoRepository extends JpaRepository<user, Integer> {
	public List<user> findByUsername(String username);
}
