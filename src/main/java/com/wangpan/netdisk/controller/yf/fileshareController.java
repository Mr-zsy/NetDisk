package com.wangpan.netdisk.controller.yf;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wangpan.netdisk.model.yf.file;
import com.wangpan.netdisk.model.yf.fileshare;
import com.wangpan.netdisk.service.yf.fileRepository;
import com.wangpan.netdisk.service.yf.fileshareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.classic.pattern.Util;
@CrossOrigin
@RestController
@RequestMapping("/friend")
public class fileshareController {
	@Autowired
	fileshareRepository fileshareres;
	@Autowired
	fileRepository fileres;
	/**
	 * 分享文件需求登陆者的用户名，接受方的用户名，文件的位置
	 * @param username
	 * @param friendname
	 * @param fileicom
	 * @return
	 */
	@PostMapping("/fileshare")
	public String sharefile(
			@RequestParam(value="username")String username,
			@RequestParam(value="friendname")String friendname,
			@RequestParam(value="fileno")String fileicom){
		fileshare a=new fileshare();
		a.setUsername(username);
		a.setFriendname(friendname);
		a.setFileicom(fileicom);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp now=new Timestamp(System.currentTimeMillis());
		String str=sdf.format(now);
		a.setTime(str);
		if((fileshareres.findByFriendnameAndFileno(friendname,fileicom).size()>0)){
			return "已经分享过该文件!";
		}
		fileshareres.save(a);
		int id=fileshareres.findByFileno(fileicom).get(0).getId();
		return "分享成功!";
	}


	@PostMapping("/findUsersFile")
	public List<file> findUsersFile(@RequestParam("username")String username,@RequestParam("friendname")String friendname){
		List<fileshare> aFileshares=fileshareres.findAllByUsernameAndFriendname(username,friendname);
		List<file> fileslists=new ArrayList<file>();
		for(int i=0;i<aFileshares.size();i++) {
			List<file> file=new ArrayList<file>();
			file=fileres.findAllByFileno(aFileshares.get(i).getFileicom());
			fileslists.addAll(file);
		}
		return fileslists;
	}
}
