import org.w3c.dom.css.CSSStyleDeclaration;

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
}
