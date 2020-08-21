package com.lzw.knowledge.knowledge.leetcode;

import java.util.ArrayList;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

 回文子串：从前往后，和从后往前的串都是一样的

 示例 1：

 输入："abc"
 输出：3
 解释：三个回文子串: "a", "b", "c"
 示例 2：

 输入："aaa"
 输出：6
 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
  

 提示：

 输入的字符串长度不会超过 1000 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/palindromic-substrings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_8 {

    public static void main(String[] args) {
//        new Solution_8().countSubstrings("aaa");
        System.out.println(5%2);
    }
    public int countSubstrings(String s) {
        int length = s.length();
        ArrayList<String> characters = new ArrayList<>();
        //获取所有的子串
        for (int i = 0; i < length; i++) {
            characters.add(String.valueOf(s.charAt(i)));
            StringBuilder bui = new StringBuilder();
            bui.append(s.charAt(i));
            for (int j = i + 1; j < length; j++) {
                bui.append(s.charAt(j));
                characters.add(bui.toString());
            }
        }
        int nums = 0;
        //判断子串是否符合回文串：反转字符串，与与字符串相同即为回文串
        for (String item : characters) {
            StringBuffer bu = new StringBuffer(item);
            bu = bu.reverse();
            boolean flag = true;
            for (int i = 0; i < item.length(); i++) {
                if(item.charAt(i) != bu.charAt(i)){
                    flag = false;
                    break;
                }
            }
            if(flag) nums++;
        }
        return nums;
    }

    public int countSubstrings2(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

}
