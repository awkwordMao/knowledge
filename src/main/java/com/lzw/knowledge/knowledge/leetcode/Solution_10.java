package com.lzw.knowledge.knowledge.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * <p>
 * 输入: "aba"
 * <p>
 * 输出: False
 * 示例 3:
 * <p>
 * 输入: "abcabcabcabc"
 * <p>
 * 输出: True
 * <p>
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_10 {

    public boolean repeatedSubstringPattern(String s) {
        for(int i = 1; i < s.length(); ++i){

            if(s.length()%i == 0){
                //找出可能的子串长度
                String t = s.substring(0, i);
                boolean flag = true;
                for(int j = i; j + i <= s.length(); j += i){
                    //对比后续子串是否相等
                    if(!t.equals(s.substring(j, j + i))){
                        flag = false;
                        break;
                    }
                }
                if(flag) return true;
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        if(s.length() == 0 || s.length() == 1) return false;
        //遍历得出有可能的子串：和第一个字符相等，并且s.length % i == 0
        List<StringBuilder> list = new ArrayList<>();
        List<StringBuilder> temp = new ArrayList<>();
        int n = s.length();
        char first = s.charAt(0);
        list.add(new StringBuilder().append(first));
        temp.add(new StringBuilder().append(first));
//        StringBuilder builder = new StringBuilder().append(first);
        StringBuilder stringBuilder = new StringBuilder().append(list.get(temp.size() - 1));
        for (int i = 1; i < n; i++) {
//            if(s.charAt(i) != first){
//            }
            stringBuilder.append(s.charAt(i));
            if(s.charAt(i) == first && n % i ==0){
                temp.add(stringBuilder);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                list.add(stringBuilder);
            }
        }

        out: for (int i = 0; i < list.size(); i++) {
            int ln = list.get(i).length();
            String str = list.get(i).toString();
            for (int j = 0; j < n; j+=ln) {
                if(!s.substring(j, j + ln).equals(str)){
                    continue out;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "abab";
        System.out.println(str.substring(2, 4));
        System.out.println(new Solution_10().repeatedSubstringPattern("abab"));;
    }
}
