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

    

}
