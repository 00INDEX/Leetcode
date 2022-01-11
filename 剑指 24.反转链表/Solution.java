import javax.lang.model.type.NullType;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

public class Solution {
    public class ListNode{
        int val;
        ListNode next = null;
        ListNode(int x){val = x;}
    }
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode L = new ListNode(-1);
        while(p != null){
            ListNode Temp = p.next;
            p.next = L.next;
            L.next = p;
            p = Temp;
        }
        return L.next;
    }
    public static void main(String args[]) {
        System.out.println((new Solution()).numOfBurgers(2, 1));
    }
}
