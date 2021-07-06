package Tree;

import java.util.*;

public class questionsBinaryTree {
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

    public int maximum(TreeNode node){
        if(node == null)
            return -(int)1e9;

        int lmv = maximum(node.left);  //left maximum value
        int rmv = maximum(node.right);  //right maximum value

        return Math.max(Math.max(lmv, rmv), node.val);
    }

    public boolean find(TreeNode node, int data){
        if(node == null)
            return false;
        if(node.val == data)
            return true;

        return find(node.left, data) || find(node.right, data);
    }

    public boolean rootToNodePath(TreeNode node, TreeNode data, ArrayList<TreeNode> ans){
        if(node == null)
            return false;

        if(node == data){
            ans.add(node);
            return true;
        }
        
        boolean res = rootToNodePath(node.left, data, ans) || rootToNodePath(node.right, data, ans);

        if(res)
            ans.add(node);

        return res;
    }

    public ArrayList<TreeNode> rootToNodePath(TreeNode node, int data){
        if(node == null)
            return new ArrayList<>();

        if(node.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<TreeNode> left = rootToNodePath(node.left, data);
        if(left.size() > 0){
            left.add(node);
            return left;
        }
        
        ArrayList<TreeNode> right = rootToNodePath(node.right, data);
        if(right.size() > 0){
            right.add(node);
            return right;
        }

        return new ArrayList<>();
    }

    //Leetcode 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;

        ArrayList<TreeNode> listOne = new ArrayList<>();
        ArrayList<TreeNode> listTwo = new ArrayList<>(); 

        rootToNodePath(root, p, listOne);
        rootToNodePath(root, q, listTwo);

        int i = listOne.size() - 1;
        int j = listTwo.size() - 1;

        TreeNode LCA = null;

        while(i >= 0 && j >= 0){
            if(listOne.get(i) != listTwo.get(j))
                break;
            
            LCA = listOne.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    //Leetcode 863
    public void printKdown(TreeNode node, int depth, TreeNode block, List<Integer> ans){
        if(node == null || depth < 0 || node == block)
            return;

        if(depth == 0){
            ans.add(node.val);
            return;
        }

        printKdown(node.left, depth - 1, block, ans);
        printKdown(node.right, depth - 1, block, ans);
    }

    public List<Integer> distanceK_(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        List<Integer> ans = new ArrayList<>();

        TreeNode blockNode = null;
        for(int i = 0; i < list.size(); i++){
            printKdown(list.get(i), k - i, blockNode, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    //Better Solution
    public int distanceK2(TreeNode node, TreeNode target, int k, List<Integer> ans){
        if(node == null)
            return -1;

        if(node == target){
            printKdown(node, k, null, ans);
            return 1;
        }

        int lans = distanceK2(node.left, target, k, ans);
        if(lans != -1){
            printKdown(node, k - lans, node.left, ans);
            return lans + 1;
        }
        
        int rans = distanceK2(node.right, target, k, ans);
        if(rans != -1){
            printKdown(node, k - rans, node.right, ans);
            return rans + 1;
        }

        return -1;
    } 

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        distanceK2(root, target, k, ans);
        return ans;
    }
    
    public int rootToNodeDistance(TreeNode node, TreeNode data){
        if(node == null)
            return -1;
            
        if(node == data)
            return 0;

        int lans = rootToNodeDistance(node.left, data);
        if(lans != -1){
            return lans + 1;
        }

        int rans = rootToNodeDistance(node.right, data);
        if(rans != -1){
            return rans + 1;
        }

        return -1;
    }

    //=======================================================================

    //Leetcode 543
    public int diameterOfBinaryTree_01(TreeNode root) { 
        if(root == null)
            return -1;

        int leftTreeDia = diameterOfBinaryTree(root.left);
        int rightTreeDia = diameterOfBinaryTree(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(Math.max(leftTreeDia, rightTreeDia), leftHeight + rightHeight + 2);
    }

    // {dia, height}
    public int[] diameterOfBinaryTree_02(TreeNode root){
        if(root == null)    
            return new int[]{-1, -1};

        int[] leftAns = diameterOfBinaryTree_02(root.left);
        int[] rightAns = diameterOfBinaryTree_02(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans;
    }

    int maxDia = 0;
    public int diameterOfBinaryTree_03(TreeNode root){
        if(root == null)    
            return -1;

        int leftHeight = diameterOfBinaryTree_03(root.left);
        int rightHeight = diameterOfBinaryTree_03(root.right);

        maxDia = Math.max(maxDia, leftHeight + rightHeight + 2);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //If the use of global variable is restricted
    public int diameterOfBinaryTree_04(TreeNode root, int[] ans){
        if(root == null)    
            return -1;

        int leftHeight = diameterOfBinaryTree_04(root.left, ans);
        int rightHeight = diameterOfBinaryTree_04(root.right, ans);

        ans[0] = Math.max(ans[0], leftHeight + rightHeight + 2);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root){
        if(root == null)
            return 0;
        // return diameterOfBinaryTree_01(root);
        // return diameterOfBinaryTree_02(root)[0];

        int[] ans = new int[1];
        diameterOfBinaryTree_04(root, ans);
        return ans[0];
    }

    //Leetcode 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        
        if(root.left == null && root.right == null)
            return targetSum - root.val == 0;
        
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //Leetcode 113
    public void pathSum(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> smallAns) {
        if(root == null)
            return;
        
        if(root.left == null && root.right == null){
            if(targetSum - root.val == 0){
                ArrayList<Integer> base = new ArrayList<>(smallAns);
                base.add(root.val);
                res.add(base);
            }
            
            return;
        }
            
        smallAns.add(root.val);
        
        pathSum(root.left, targetSum - root.val, res, smallAns);
        pathSum(root.right, targetSum - root.val, res, smallAns);
        
        smallAns.remove(smallAns.size() - 1);
        
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        
        pathSum(root, targetSum, res, smallAns);
        
        return res;
    }

    //Maximum Path Sum between 2 Leaf Nodes 
    class Node{
        int data;
        Node left, right;

        Node(int item){
            data = item;
            left = right = null;
        }
    } 

    int maxLeafToLeaf = -(int)1e9;
    int maxPathSum_(Node root){ 
        if(root == null)
            return -(int)1e9;
            
        if(root.left == null && root.right == null)
            return root.data;
            
        int LNTLMS = maxPathSum_(root.left);
        int RNTLMS = maxPathSum_(root.right);
    
        if(root.left != null && root.right != null)        
            maxLeafToLeaf = Math.max(maxLeafToLeaf, LNTLMS + RNTLMS + root.data);
            
        return Math.max(LNTLMS, RNTLMS) + root.data;
    }
    
    int maxPathSum(Node root){
        // if(root == null)
        //     return Integer.MIN_VALUE;
            
        maxPathSum_(root);
        return maxLeafToLeaf;
    }

    //Leetcode 124
    int maxNTN = -(int)1e9;
    public int maxPathSum_(TreeNode root) {
        if(root == null)
            return 0;

        int leftMaxPathSum = maxPathSum_(root.left);
        int rightMaxPathSum = maxPathSum_(root.right);

        int maxSumTillRoot = Math.max(leftMaxPathSum, rightMaxPathSum) + root.val;
        maxNTN = Math.max(Math.max(maxNTN, maxSumTillRoot), Math.max(root.val, leftMaxPathSum + root.val + rightMaxPathSum));

        return Math.max(maxSumTillRoot, root.val);
    }

    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;

        maxPathSum_(root);
        return maxNTN;
    }

    //Leetcode 98
    public class BSTpair{
        boolean isBST = true;
        long max = -(long)1e13;
        long min = (long)1e13;

        BSTpair(boolean isBST, long max, long min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }

        BSTpair() {

        }
    }

    public BSTpair isValidBST_(TreeNode root) {
        if(root == null)
            return new BSTpair();

        BSTpair lres = isValidBST_(root.left);
        BSTpair rres = isValidBST_(root.right);

        BSTpair myres = new BSTpair();
        myres.isBST = lres.isBST && rres.isBST && lres.max < root.val && root.val < rres.min;

        if(!myres.isBST)
            return myres;

        myres.max = Math.max(rres.max, root.val);
        myres.min = Math.min(lres.min, root.val);

        return myres;
    }

    public boolean isValidBST(TreeNode root){
        return isValidBST_(root).isBST;
    }

    //Leetcode 99
    TreeNode a = null, b = null, prev = null;
    public boolean recoverTree_(TreeNode root) {
        if(root == null)
            return true;

        if(!recoverTree_(root.left))
            return false;

        if(prev != null && prev.val > root.val){
            b = root;
            if(a == null)
                a = prev;
            else 
                return false;
        }

        prev = root;
        if(!recoverTree_(root.right))
            return false;

        return true;
    }

    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        if(a != null){
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

}