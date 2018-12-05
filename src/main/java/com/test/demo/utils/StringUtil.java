package com.test.demo.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by root on 3/28/17.
 */
public class StringUtil {
    public static int StringArrayDistinctedSize(String[] strings) {
        HashSet<String> strSet = new HashSet<String>();
        for (String string : strings) {
            String[] splited = string.split("\\s+");
            Collections.addAll(strSet, splited);
        }
        return strSet.size();
    }

    public static TreeSet<String> StringArrayDistincted(String[] strings) {
        TreeSet<String> strSet = new TreeSet<String>();
        for (String string : strings) {
            String[] splited = string.split("\\s+");
            Collections.addAll(strSet, splited);
        }
        return strSet;
    }

    public static String[] StringtoArray(String str) {
        TreeSet<String> strSet = new TreeSet<String>();
        String[] splited = str.split("\\s+");
        Collections.addAll(strSet, splited);
        return splited;

    }

    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
