package com.fintecher.util;

import java.util.Random;

public class RandomUtil {
   static Random rnd = new Random();

    public static String random6() {
        Integer i =(int)(Math.random() * 1000000 );
        if ( i < 99999 ) {

            i += 100000;
        }

        return i.toString();
    }


    /**
     * 逐位生成随机整数
     * @param digCount 位数
     * @return
     */
    public static String getRandomNumber(int digCount) {
        StringBuilder sb = new StringBuilder(digCount);
        for(int i=0; i < digCount; i++)
            sb.append((char)('0' + rnd.nextInt(10)));
        return sb.toString();
    }

}
