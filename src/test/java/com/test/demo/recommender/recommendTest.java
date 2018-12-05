package com.test.demo.recommender;

import java.util.Arrays;

/**
 * Created by legotime
 */
public class recommendTest {
    public static void main(String[] args) {
        int[] a = {1, 9, 10};

       String[] course =  {"212 213 214 216", "203 204", "204 205 222 227 229 230 231 232"};
        System.out.println(Arrays.toString(course));
        System.out.println(Arrays.toString(a));
//        String[] products = {"dog cate pen cake pie","dog cat water coco","pie cake pen cate"};
//        String s = "pie cake pen cate";
       String s = "203 204";
        recommend rec = new recommend();
        rec.fit(course);
        double[] doubles = rec.recommendFun(s);
        System.out.println(Arrays.toString(doubles));
        System.out.println(rec.getProduct());
        //[cake, cat, cate, coco, dog, pen, pie, water]
        //[0.0, 0.0, 0.0, 0.0, 0.8, 0.0, 0.0, 0.0]

        //所以推荐　买了　pie cake pen cate　的人买 dog
    }

}