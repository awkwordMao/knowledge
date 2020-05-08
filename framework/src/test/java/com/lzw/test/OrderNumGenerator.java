package com.lzw.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 16
 * @Description:
 */
public class OrderNumGenerator {
    //全局订单id
    public  static int count = 0;

    //生成订单ID
    public   String getNumber() {
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }
}
