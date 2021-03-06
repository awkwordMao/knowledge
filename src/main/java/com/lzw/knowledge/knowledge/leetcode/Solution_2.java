package com.lzw.knowledge.knowledge.leetcode;

/**
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。

 示例 1:

 输入: [0,1]
 输出: 2
 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 示例 2:

 输入: [0,1,0]
 输出: 2
 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
  

 注意: 给定的二进制数组的长度不会超过50000。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/contiguous-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_2 {

    public int findMaxLength(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i] != nums[i+1]){
                temp++;
            }
        }
        return temp;
    }
}
