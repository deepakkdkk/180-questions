// 25. Reverse Linkedlist
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }
}

// 26. Middle of the linked list
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

// 27. Merge two sorted list
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode first = list1;
        ListNode second = list2;
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        while(first != null && second != null){
            if(first.val <= second.val){
                ListNode fnext = first.next;
                dummy.next = first;
                dummy = dummy.next;
                first = fnext;
            }else{
                ListNode snext = second.next;
                dummy.next = second;
                dummy = dummy.next;
                second = snext;
            }
        }
        if(first != null){
            dummy.next = first;
        }
        if(second != null){
            dummy.next = second;
        }
        return ans.next;
    }
}