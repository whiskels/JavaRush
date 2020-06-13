package com.javarush.task.task22.task2208;

import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        String value;
        for (Map.Entry<String, String> e : params.entrySet()) {
            if ((value = e.getValue()) != null) {
                if (sb.length() != 0)
                    sb.append(" and ");
                sb.append(e.getKey() + " = '" + value + "'");
            }
        }

        return new String(sb);
    }
}
