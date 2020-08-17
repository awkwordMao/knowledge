package com.lzw.knowledge.knowledge.leetcode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。

 本题中，一棵高度平衡二叉树定义为：

 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

 示例 1:

 给定二叉树 [3,9,20,null,null,15,7]

 3
 / \
 9  20
 /  \
 15   7
 返回 true 。

 示例 2:

 给定二叉树 [1,2,2,3,3,null,null,4,4]

 1
 / \
 2   2
 / \
 3   3
 / \
 4   4
 返回 false 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_6 {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    /**
     * 从底部开始递归遍历
     * @param node
     * @return
     */
    public int height(TreeNode node){
        if(node == null) return 0;
        int leftHeight = height(node.leftNode);
        int rightHeight = height(node.rightNode);
        if(Math.abs(leftHeight - rightHeight) > 1 || leftHeight == -1 || rightHeight == -1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.leftNode = new TreeNode(2);
        treeNode.rightNode = new TreeNode(2);
//        treeNode.leftNode.leftNode
    }
}
