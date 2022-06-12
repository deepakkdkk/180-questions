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

// 33. Reverse a Linkedlist in a group of Size k
// Approach 1 : Recursive  T.C = O(n), S.C = O(n/k) <- recursion stack space
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1){
            return head;
        }
        int t = k;
        ListNode curr = head;
        while(t-- > 1){
            
            curr = curr.next;
            if(curr == null){
                return head;
            }
        }
        ListNode fhead = curr.next;
        curr.next = null;
        ListNode recAns = reverseKGroup(fhead, k);
        ListNode revAns = reverseList(head);
        head.next = recAns;
        return revAns;
        
    }
        public ListNode reverseList(ListNode head){
            ListNode curr = head, prev = null, forw = null;
            while(curr != null){
                forw = curr.next;
                curr.next = prev;
                
                prev = curr;
                curr = forw;
            }
            return prev;
        }  
}

// Approach 2 : iterative  T.C = O(n), S.C = O(1)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1){
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = dummy;
        ListNode forw = dummy;
        int count = 0;
        while(curr.next != null){
            curr = curr.next;
            count++;
        }
        while(count >= k){
            curr = prev.next;
            forw = curr.next;
            for(int i = 1; i < k; i++){ // k - 1 link reverse for k =3 , 2 link we are reversing 
                curr.next = forw.next;
                forw.next = prev.next;
                prev.next = forw;
                forw = curr.next;
            }
            prev = curr;
            count -= k;
        }
        return dummy.next;
    }
}

// 34. Palindrome linked list

// Approach 1 : use extra space and reverse linked list and then iterate or (store in arraylist and check palindrome)
// Approach 2 : T.C = O(n/2(finding mid node) + n / 2(reversing other half) + n / 2 (again iterating upto mid node)) = O(3n/2)
// if linked list doesn't want to modify then again reverse it after mid.
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        
        ListNode mid = mid(head);
        ListNode rev = reverse(mid.next);
        mid.next = rev;
        ListNode first = head;
        ListNode second = rev;
        while(second != null && first.val == second.val){
            second = second.next;
            first = first.next;
        }
        if(second == null) return true;
        return false;
        
    }
    
    public ListNode mid(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    public ListNode reverse(ListNode head){
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

// 35. Find the starting point of the loop of the linked list

// Approach 1 : use hashset  T.C = O(n), S.C = O(n)
// Approach 2 : using 2 point slow and fast , T.C = O(n), S.C = O(1)
// slow covered distance = l1 + l2, fast covered distance = l1 + l2 + nC where C is no.of turns
// using speed distance formula : 2 (l1 + l2) = l1 + l2 + nC , therefore l1 = nC - l2
image.png

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
    
        ListNode slow  = head;
        ListNode fast  = head;
        ListNode entry = head;

        while (fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {                      // there is a cycle
                while(slow != entry) {               // found the entry location
                    slow  = slow.next;
                    entry = entry.next;
                }
                return entry;
            }
        }
        return null;       
    }
}

