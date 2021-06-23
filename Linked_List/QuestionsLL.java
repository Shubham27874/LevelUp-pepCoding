import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.ls.LSException;

public class QuestionsLL {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // Leetcode 206
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    public ListNode reverseListRec(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode temp = head.next;
        head.next = null;

        ListNode rl = reverseListRec(temp);
        temp.next = head;

        return rl;
    }

    // Leetcode 234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;

        ListNode midNode = midNode(head);
        ListNode nHead = midNode.next;
        midNode.next = null;

        nHead = reverseList(nHead);

        ListNode curr1 = head;
        ListNode curr2 = nHead;

        boolean res = true;
        while(curr1 != null && curr2 != null){
            if(curr1.val != curr2.val){
                res = false;
                break;
            }

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        nHead = reverseList(nHead);
        midNode.next = nHead;

        return res;
    }

    public void dataReverse(ListNode head){
        if (head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);

        ListNode curr1 = head;
        ListNode curr2 = nhead;

        while (curr1 != null && curr2 != null)
        {
            int temp = curr1.val;
            curr1.val = curr2.val;
            curr2.val = temp;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        nhead = reverseList(nhead);
        mid.next = nhead;
    }

    //Leetcode 143
    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;

        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;

        nhead = reverseList(nhead);

        ListNode c1 = head;
        ListNode c2 = nhead;

        ListNode f1 = null;
        ListNode f2 = null;
        
        while(c1 != null && c2 != null){
            f1 = c1.next;
            f2 = c2.next;

            c1.next = c2;
            c2.next = f1;

            c1 = f1;
            c2 = f2;
        }

        // cout<<"Reorder List: ";
        // printList(head);

        // cout<<"Actual List: ";
        // againReorderList(head);
        // printList(head);
    }

    void printList(ListNode node){
        ListNode curr = node;
        while (curr != null){
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    void againReorderList(ListNode head){
        if (head == null || head.next == null)
            return;

        ListNode h1 = head;
        ListNode h2 = head.next;

        ListNode c1 = h1;
        ListNode c2 = h2;

        while (c2 != null && c2.next != null) {
            ListNode f = c2.next; // Backup

            c1.next = f; // links
            c2.next = f.next;

            c1 = c1.next;
            c2 = c2.next;
        }

        // c1.next = null;
        h2 = reverseList(h2);
        c1.next = h2;
    }

    //Leetcode 21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return l1 != null ? l1 : l2;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        ListNode c1 = l1;
        ListNode c2 = l2;

        while(c1 != null && c2 != null){
            if(c1.val <= c2.val){
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }

            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;

        ListNode h = dummy.next;
        dummy.next = null;
        return h;
    }

    //Leetcode 148
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode midnode = midNode(head);
        ListNode nhead = midnode.next;
        midnode.next = null;

        ListNode h =  mergeTwoLists(sortList(head), sortList(nhead));

        return h;
    }

    //Leetcode 23
    public ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if(si == ei)
            return lists[si];

        int mid = (si + ei) / 2;
        ListNode l1 = mergeKLists(lists, si, mid);
        ListNode l2 = mergeKLists(lists, mid + 1, ei);

        return mergeTwoLists(l1, l2);
    }

    //NKlog(K)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }

    //N(K^2)
    public ListNode mergeKLists_(ListNode[] lists) {
        if(lists.length == 0)
            return null;
            
        ListNode refList = null;
        for(int i = 0; i < lists.length; i++){
            refList = mergeTwoLists(refList, lists[i]);
        }

        return refList;
    }

    //Leetcode 141
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow)
                return true;
        }

        return false;
    }

    //Leetcode 142
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow)
                break;
        }

        if(fast != slow)
            return null;

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    //Leetcode 160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        ListNode tail = headA;
        while(tail.next != null){
            tail = tail.next;
        }

        tail.next = headB;

        ListNode ans = detectCycle(headA);
        tail.next = null;

        return ans;
    }

    public int lengthOfLL(ListNode head){
        if(head == null)
            return 0;

        int count = 0;
        ListNode temp = head;
        while(temp != null){
            temp = temp.next;
            count++;
        }

        return count;
    }

    public ListNode getIntersectionNode_anotherApproach(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        int l1 = lengthOfLL(headA);
        int l2 = lengthOfLL(headB);

        ListNode biggerList = l1 > l2 ? headA : headB;
        ListNode smallerList = l1 > l2 ? headB : headA;

        int diff = Math.max(l1, l2) - Math.min(l1, l2);
        while(diff-- > 0){
            biggerList = biggerList.next;
        }

        while(biggerList != smallerList){
            biggerList = biggerList.next;
            smallerList = smallerList.next;
        }

        return biggerList;

    }

    //Leetcode 19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return head;

        ListNode c1 = head;
        ListNode c2 = head;

        while(n-- > 0){
            c2 = c2.next;
        }

        if(c2 == null){   //1st node ko remove karne ki baat
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            return head;
        }

        while(c2.next != null){
            c1 = c1.next;
            c2 = c2.next;
        }

        ListNode temp = c1.next;
        c1.next = c1.next.next;
        temp.next = null;

        return head;
    }

    //Leeetcode 25
    ListNode th = null;
    ListNode tt = null;

    public void addfirstNode(ListNode node){
        if(th == null){
            th = node;
            tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1)
            return head;

        ListNode oh = null;
        ListNode ot = null;

        int len = lengthOfLL(head);
        ListNode curr = head;

        while(len >= k){
            int tempK = k;
            while(tempK -- > 0){
                ListNode forw = curr.next;
                curr.next = null;
                addfirstNode(curr);
                curr = forw;
            }

            if(oh == null){
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            tt = null;
            th = null;

            len -= k;
        }

        ot.next = curr;
        return oh;
    }

    //Leetcode92
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        int idx = 1;

        while(curr != null){
            while(idx >= m && idx <= n){
                ListNode forw = curr.next;
                curr.next = null;
                addfirstNode(curr);
                curr = forw;
                idx++;
            }

            if(idx > n){
                if(prev != null){
                    prev.next = th;
                    tt.next = curr;
                    return head;
                } else {
                    tt.next = curr;
                    return th;
                }
            }

            idx++;
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    //Leetcode 138
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public void copyNodes(Node head){
        Node curr = head;
        while(curr != null){
            Node forw = curr.next;

            Node node = new Node(curr.val);

            node.next = forw;
            curr.next = node;

            curr = forw;
        }
    }

    public void setRandomPointer(Node head){
        Node curr = head;

        while(curr != null){
            if(curr.random != null)
                curr.next.random = curr.random.next;

            curr = curr.next.next;
        }
    }

    public Node extractLL(Node head){
        Node curr = head;
        Node dummy = new Node(-1);
        Node copyCurr = dummy;

        while(curr != null){
            copyCurr.next = curr.next;
            curr.next = curr.next.next;

            curr = curr.next;
            copyCurr = copyCurr.next;
        }

        return dummy.next;
    }

    public Node copyRandomList(Node head) {
        if(head == null)
            return head;
            
        copyNodes(head);
        setRandomPointer(head);

        return extractLL(head);
    }

    //https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    Node divide(int N, Node head){
        Node dummyOdd = new Node(-1);
        Node dummyEven = new Node(-1);    
        Node oc = dummyOdd;
        Node ec = dummyEven;

        Node curr = head;
        while(curr != null){
            if(curr.val % 2 == 0){
                ec.next = curr;
                ec = ec.next;
            } else {
                oc.next = curr;
                oc = oc.next;
            }

            curr = curr.next;
        }

        ec.next = dummyOdd.next;
        oc.next = null;

        return dummyEven.next;
    }

    
}
