import java.util.*;


public class Tree01 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int size(TreeNode node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public int height(TreeNode node) { //For Nodes = return node == null ? 0 : ............
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1; //In term of edges
    }

    public int maximum(TreeNode node) {
        if(node == null) 
            return -(int)1e9;
        int lmv = maximum(node.left); //lmv : left maximum value
        int rmv = maximum(node.right); //rmv : right maximum value

        return Math.max(Math.max(lmv, rmv) , node.val);
    }

    public int minimum(TreeNode node) {
        if(node == null) 
            return -(int)1e9;
        int lmv = minimum(node.left); //lmv : left minimum value
        int rmv = minimum(node.right); //rmv : right minimum value

        return Math.min(Math.min(lmv, rmv) , node.val);
    }

    public boolean find(TreeNode node, int data){
        if(node == null)
            return false;
        if(node.val == data)
            return true;
    
        return find(node.left, data) || find(node.right, data);    
    }

    //better than next one
    public boolean nodeToRootPath1(TreeNode node, TreeNode data, ArrayList<TreeNode> res){
        if(node == null){
            return false;
        }

        if(node == data){
            res.add(node);
            return true;
        }

        boolean ans = nodeToRootPath1(node.left, data, res) || nodeToRootPath1(node.right, data, res);
        
        if(ans){
            res.add(node);
        }

        return ans;
    }
    
    public ArrayList<TreeNode> rootToNodePath2(TreeNode node, TreeNode data){
        if(node == null){
            return new ArrayList<>();
        }

        if(node == data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<TreeNode> leftAns = rootToNodePath2(node.left, data);
        if(leftAns.size() > 0){
            leftAns.add(node);
            return leftAns;
        }

        ArrayList<TreeNode> rightAns = rootToNodePath2(node.right, data);
        if(rightAns.size() > 0){
            rightAns.add(node);
            return rightAns;
        }

        return new ArrayList<>();
    }

    //LeetCode 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();

        nodeToRootPath1(root, p, list1);
        nodeToRootPath1(root, q, list2);
        
        TreeNode lca = null;

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        while(i >= 0 && j >= 0){
            if(list1.get(i) != list2.get(j)){
                break;
            }

            lca = list1.get(i);

            i--;
            j--;
        }

        return lca;
    }

    



}