package com.wangpan.netdisk.service.yf;

import java.util.List;

import com.wangpan.netdisk.model.Files;
import com.wangpan.netdisk.model.yf.file;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fileRepository extends JpaRepository<file, Integer> {

	public List<file> findAllByFileno(String fileno);

}