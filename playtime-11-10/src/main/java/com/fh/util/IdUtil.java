package com.fh.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 雪花算法：用于生成订单，在订单头部加上日期以便于查看
 * baomidou里自带。
 *
 */
public class IdUtil {

    public static String createId(){
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmm");
        String format = sim.format(new Date());
        return format+ IdWorker.getId();
    }

    public static void main(String[] args) {

        System.out.println(createId());
        //System.out.println(IdWorker.getId());
    }


}
