package Tree;

public class AVL {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        int height = 0;
        int bal = 0;

        TreeNode(int val) { 
            this.val = val; 
        }
    }

    public static void updateHeightAndBalance(TreeNode node){
        if(node == null)
            return;

        int lh = -1;
        int rh = -1;

        if(node.left != null)
            lh = node.left.height;
        if(node.right != null)
            rh = node.right.height;

        node.bal = (lh - rh);
        node.height = Math.max(lh, rh) + 1;
    }

    public static TreeNode rightRotation(TreeNode A){
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

    public static TreeNode leftRotation(TreeNode A){ //O(1)
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode node){  //O(1)
        updateHeightAndBalance(node);
        if(node.bal == 2){
            if(node.left.bal == 1){   //ll
                return rightRotation(node);
            } else{ // lr
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        } else if(node.bal == -2) {
            if(node.right.bal == -1){  // rl
                return leftRotation(node);
            } else {  //rr
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }

        return node;
    }

    //======================Basic BST===========================
    public int maxElement(TreeNode root){
        TreeNode curr = root;
        while(curr.right != null){
            curr = curr.right;
        }

        return curr.val;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {  //recursively
        if(root == null)
            return new TreeNode(val);

        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        else   
            root.right = insertIntoBST(root.right, val);

        root = getRotation(root);
        return root;
    }

    //Leetcode 450
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
            
        if(root.val > key)
            root.left = deleteNode(root.left, key);
        else if(root.val < key)
            root.right = deleteNode(root.right, key);
        else{
            if(root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maxValue = maxElement(root.left);
            root.val = maxValue;

            root.left = deleteNode(root.left, maxValue);
        }

        return getRotation(root);
    }
}
