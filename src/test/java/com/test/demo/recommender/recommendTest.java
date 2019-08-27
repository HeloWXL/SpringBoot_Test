package com.test.demo.recommender;

import java.util.Arrays;

/**
 * Created by legotime
 */
public class recommendTest {
    public static void main(String[] args) {
//        a数组表示用户的ID
        int[] a = {1, 9, 10};

        String[] products = {"201 204 206 207 208","210 206 205 204","206 205 207 203"};
        String s = "201 206 204 208";
        recommend rec = new recommend();
        rec.fit(products);
        double[] doubles = rec.recommendFun(s);
//        打印相似度
        System.out.println("打印相似度："+Arrays.toString(doubles));
//        得到对应的课程表编号
        System.out.println("打印相对应的课程编号："+rec.getProduct());
//        找到最大相似度编号
        double max= doubles[0];
        int y = 0;
        for(int i=0;i<doubles.length;i++) {
            if (max < doubles[i]) {
                max = doubles[i];
                y=i;
            }
        }
        Object[] ob=rec.getProduct().toArray();
        System.out.println("所以推荐课程编号为："+ob[y]);
    }


}