package Tree;

import java.util.*;

public class questionsTree {
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

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
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
}
