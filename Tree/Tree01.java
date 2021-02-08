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
    public boolean rootToNodePath1(TreeNode node, TreeNode data, ArrayList<TreeNode> res){
        if(node == null){
            return false;
        }

        if(node == data){
            res.add(node);
            return true;
        }

        boolean ans = rootToNodePath1(node.left, data, res) || nodeToRootPath1(node.right, data, res);
        
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

        rootToNodePath1(root, p, list1);
        rootToNodePath1(root, q, list2);
        
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

    public void printKdown(TreeNode node, TreeNode block, int depth, List<Integer> ans){
        if(node == null || depth < 0 || node == block){
            return;
        }

        if(depth == 0){
            ans.add(node.val);
            return;
        }

        printKdown(node.left, block, depth - 1, ans);
        printKdown(node.right, block, depth - 1, ans);
    }

    //LeetCode 863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list = new ArrayList<>();
        rootToNodePath1(root, target, list);

        List<Integer> ans  = new ArrayList<>();
        TreeNode blockNode = null;

        for(int i = 0; i < list.size(); i++){
            printKdown(list.get(i), blockNode, K - i, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    //Better Approach
    public int distanceK2(TreeNode node, TreeNode target, int K, List<Integer> ans){
        if (node == null)
            return -1;
        if (node == target){
            printKdown(node, null, K, ans);
            return 1;
        }

        int lans = distanceK2(node.left, target, K, ans);
        if (lans != -1) {
            printKdown(node, node.left, K - lans, ans);
            return lans + 1;
        }

        int rans = distanceK2(node.right, target, K, ans);
        if (rans != -1) {
            printKdown(node, node.right, K - rans, ans);
            return rans + 1;
        }

        return -1;
    }
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        ArrayList<Integer> list = new ArrayList<>();
        distanceK2(root, target, K, list);
        return list;
    }

    public int rootToNodeDistance(TreeNode node, TreeNode data){
        if(node == null)
            return -1;

        if(node == data)
            return 0;

        int lans = rootToNodeDistance(node.left, data);
        if (lans != -1)
            return lans + 1;

        int rans = rootToNodeDistance(node.right, data);
        if (rans != -1)
            return rans + 1;

        return -1;
    }
}