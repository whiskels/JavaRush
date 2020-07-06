package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
        public InnerClass() {
        }
    }

    public static Solution[] getTwoSolutions() {
        Solution[] result = {new Solution(), new Solution()};
        result[0].innerClasses[0] = result[1].innerClasses[0] = new Solution().new InnerClass();
        result[0].innerClasses[1] = result[1].innerClasses[1] = new Solution().new InnerClass();

        return result;
    }

    public static void main(String[] args) {

    }
}
