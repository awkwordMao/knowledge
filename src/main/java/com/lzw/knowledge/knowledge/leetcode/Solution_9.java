package com.lzw.knowledge.knowledge.leetcode;

/**
 * 给定一个二叉树，找出其最小深度。

 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

 说明: 叶子节点是指没有子节点的节点。

 示例:

 给定二叉树 [3,9,20,null,null,15,7],

     3
   / \
 9    20
    /  \
  15   7
 返回它的最小深度  2.
      1
    /
   2
 返回最小深度：2
 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_9 {

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int leftHeight = minDepth(root.leftNode);
        int rightHeight = minDepth(root.rightNode);
        if((leftHeight == 0 && rightHeight == 0) || (leftHeight != 0 && rightHeight != 0)){
            return Math.min(leftHeight, rightHeight) + 1;
        }else{
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, null), null);
        System.out.println(new Solution_9().minDepth(treeNode));;
    }
}
