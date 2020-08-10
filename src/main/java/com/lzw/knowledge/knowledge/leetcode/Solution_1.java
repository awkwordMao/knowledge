package com.lzw.knowledge.knowledge.leetcode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 示例：
 输入：1->2->4, 1->3->4
 输出：1->1->2->3->4->4
 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_1 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7))));
        ListNode listNode2 = new ListNode(1, new ListNode(4, new ListNode(5, new ListNode(6))));
        ListNode result = mergeTwoLists(listNode1, listNode2);
        System.out.println(result);
    }
}
