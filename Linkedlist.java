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

// 28. Remove nth node from end of the list
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(n > 0){
            fast = fast.next;
            n--;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

// 29. Add two numbers
// time complexity = O(max(l1, l2))  S.C = O(max(l1, l2) + 1)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        int c = 0;
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + c;
            c = sum / 10;
            int rem = sum % 10;
            ListNode temp = new ListNode(rem);
            dummy.next = temp;
            dummy = dummy.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + c;
            c = sum / 10;
            int rem = sum % 10;
            
            ListNode temp = new ListNode(rem);
            dummy.next = temp;
            dummy = dummy.next;
            l1 = l1.next;
        }
        while(l2 != null){
            int sum = l2.val + c;
            c = sum / 10;
            int rem = sum % 10;
            
            ListNode temp = new ListNode(rem);
            dummy.next = temp;
            dummy = dummy.next;
            l2 = l2.next;
        }
        if(c > 0){
            ListNode temp = new ListNode(c);
            dummy.next = temp;
            dummy = dummy.next;
        }
        return ans.next;
    }
}

// 30. Delete a given Node in linked List O(1) solution
class Solution {
    public void deleteNode(ListNode node) {
        int val = node.next.val;
        node.val = val;
        node.next = node.next.next;
    }
}

// 31. Find intersection point of Y linkedlist
// Approach 1 : T.C = O(m * n)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode slow = headA;
        
        while(slow != null){
            ListNode fast = headB;
            while(fast != null && fast != slow){
                fast = fast.next;
            }
            if(fast == slow){
                return fast;
            }
            slow = slow.next;
        }
        return null;
    }
}

// Approach 2 : T.C = O(2 * m) generally due to hashset but in worst case T.C = O(m ^ 2), S.C = O(m)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Time complexity = O(m + n)  S.C = O(m or n)
        HashSet<ListNode> hs = new HashSet<>();
        ListNode slow = headA;
        while(slow != null){
            hs.add(slow);
            slow = slow.next;
        }
        ListNode fast = headB;
        while(fast != null){
            if(hs.contains(fast)){
                return fast;
            }
            fast = fast.next;
        }
        return null;
    }
}

// Approach 3: T.C = O(m + (m + n)), S.C = O(1) . m + n is cycle time complexity
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        boolean cycle = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                cycle = true;
                break;
            }
        }
        if (!cycle) {
            return null;

        }
        s = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    // INTERSECTION OF 2 LINKED LIST

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode c = headA;
        while (c.next != null) {
            c = c.next;
        }
        c.next = headA;
        ListNode ans = detectCycle(headB);
        c.next = null;
        return ans;
    }

}

// Approach 4: T.C = O(2 * m) , S.C = O(1)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if(headA == null || headB == null){
            return null;
        }
        ListNode d1 = headA;
        ListNode d2 = headB;
        
        while( d1 != d2){
            d1 = d1 == null ? headB : d1.next;
            d2 = d2 == null ? headA : d2.next;
        }
        
        return d1;
    }
}

// 32. Detect a cycle in Linked List
// Approach 1: T.C. = O(n) generally, S.C = O(n)
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        HashSet<ListNode> hs = new HashSet<>();
        while(head != null){
            if(hs.contains(head)) return true;
            hs.add(head);
            head = head.next;
        }
        return false;
    }
}

// Approach 2 : T.C = O(n) , S.C = O(1)
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

// 33. 
