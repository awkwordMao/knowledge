package com.lzw.knowledge.knowledge.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 示例:

 给定的有序链表： [-10, -3, 0, 5, 9],

 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

        0
       / \
     -3   9
    /   /
 -10  5
 */
public class Solution_7 {

    public TreeNode sortedListToBST(ListNode head) {
        //
//        return buildTree(head, null);
        ListNode temp = new ListNode();
        List<ListNode> list = new ArrayList();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        return new Solution_7().buildTree2(list);
    }

    public TreeNode buildTree(ListNode left, ListNode right){
        if(left == right) return null;
        //找中位数节点
        ListNode median = getMedian(left, left.next);
        TreeNode root = new TreeNode(median.val);
        root.leftNode = buildTree(left, median);
        root.rightNode = buildTree(median, right);
        return root;
    }

    /**
     * 获取链表结构的中位数：使用快慢指针法，快指fast针向右移动两个位置，满指针slow向右移动一个位置，当快指针的位置到达右端点
     * 或者下一个节点就是右端点，此时，慢指针指向的节点就是中位数。
     * @param left
     * @param right
     * @return
     */
    public ListNode getMedian(ListNode left, ListNode right){
        ListNode fast = left;
        ListNode slow = left;
        while(fast != right && fast.next != right){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 转换成有序集合
     * @param list
     * @return
     */
    public TreeNode buildTree2(List<ListNode> list){

        if(list.isEmpty()) return null;
        int lentgh = list.size();
        TreeNode root = new TreeNode(list.get(lentgh/2).val);
        root.leftNode = buildTree2(list.subList(0, lentgh/2));
        root.rightNode = buildTree2(list.subList(lentgh/2+1, lentgh));
        return root;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.subList(0,4).toString());
    }
}
