package com.wangpan.netdisk.model.yf;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.Serializable;




@DataJpaTest
public class primary implements Serializable {
	private String username;
	private String friendname;

}
