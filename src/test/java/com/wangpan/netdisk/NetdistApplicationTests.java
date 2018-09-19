package com.wangpan.netdisk;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/*
@RunWith(SpringRunner.class)
@SpringBootTest*/
public class NetdistApplicationTests {

    @Test
    public void contextLoads() {
        String str ="123";

        System.out.println(Base64.encodeBase64(str.getBytes()));
    }

}
