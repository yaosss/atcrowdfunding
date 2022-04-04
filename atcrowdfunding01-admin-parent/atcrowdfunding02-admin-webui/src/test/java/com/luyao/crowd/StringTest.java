package com.luyao.crowd;

import com.luyao.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @Author yao
 * @create 2022-03-27
 */
public class StringTest {
    @Test
    public void Md5Test(){
        String Source = "123456";
        String encode = CrowdUtil.encode(Source);
        System.out.println(encode);
    }
}
