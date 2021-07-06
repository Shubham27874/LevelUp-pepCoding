package Tree;

public class BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { 
            this.val = val; 
        }
    }

    public int size(TreeNode node){
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public int height(TreeNode node){
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public int maxElement(TreeNode root){
        TreeNode curr = root;
        while(curr.right != null){
            curr = curr.right;
        }

        return curr.val;
    }

    public int minElement(TreeNode root){
        TreeNode curr = root;
        while(curr.left != null){
            curr = curr.left;
        }

        return curr.val;
    }

    public boolean findData(TreeNode node, int data){
        TreeNode curr = node;
        while(curr != null){
        if(curr.val == data)
            return true;
        else if(curr.val > data)
            curr = curr.left;
        else 
            curr = curr.right;
        }

        return false;
    }

    public boolean findData2(TreeNode node, int data){
        if(node == null)
            return false;
            
        if(node.val == data)
            return true;

        if(node.val > data)
            return findData2(node.left, data);
        else    
            return findData2(node.right, data);
    }

    //Leetcode 701
    public TreeNode insertIntoBST(TreeNode root, int val) {  //recursively
        if(root == null)
            return new TreeNode(val);

        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        else   
            root.right = insertIntoBST(root.right, val);

        return root;
    }

    public TreeNode insertIntoBST_02(TreeNode root, int val) {   //iteratively
        TreeNode curr = root;
        TreeNode prev = null;
        
        while(curr != null){
            prev = curr;
            if(curr.val > val)  
                curr = curr.left;
            else    
                curr = curr.right;
        }

        TreeNode node = new TreeNode(val);
        if(prev == null)
            return node;
        else if(prev.val < val)
            prev.right = node;
        else
            prev.left = node;
            
            
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

        return root;
    }

    //Leetcode 235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.val > p.val && curr.val > q.val)    
                curr = curr.left;
            else if(curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else 
                return curr;
        }

        return null;
    }

    public TreeNode createBST(int[] arr ,int si, int ei){ //sorted array
        if(si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        node.left = createBST(arr, si, mid - 1);
        node.right = createBST(arr, mid + 1, ei);

        return node;
    }
}