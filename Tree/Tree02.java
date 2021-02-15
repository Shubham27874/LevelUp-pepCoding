public class Tree02 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int height(TreeNode node) { 
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public int diameterOfBinaryTree_1(TreeNode root) {
        if (root == null)
            return -1;

        int leftDia = diameterOfBinaryTree_1(root.left);
        int rightDia = diameterOfBinaryTree_1(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(leftDia, rightDia), lh + rh + 2);
    }

    //{Diameter, Height}
    public int diameterOfBinaryTree_2(TreeNode root){
        if(root == null)
            return new int[] { -1, -1 };

        int[] leftAns = diameterOfBinaryTree_2(root.left);
        int[] rightAns = diameterOfBinaryTree_2(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans[0];
    }

    int maxDia = 0;
    public int diameterOfBinaryTree_3(TreeNode root){
        if(root == null)
            return -1;

        int lh = diameterOfBinaryTree_3(root.left);
        int rh = diameterOfBinaryTree_3(root.right);

        maxDia = Math.max(maxDia, lh + rh + 2);

        return Math.max(lh ,rh) + 1;
    }

    //Without using global variable
    public int diameterOfBinaryTree_4(TreeNode root, int[] ans){
        if(root == null)
            return -1;

        int lh = diameterOfBinaryTree_4(root.left, ans);
        int rh = diameterOfBinaryTree_4(root.right, ans);

        ans[0] = Math.max(ans[0], lh + rh + 2);

        return Math.max(lh ,rh) + 1;
    }


    public int diameterOfBinaryTree(TreeNode root){
        if(root == null)
            return 0;
        
        //return diameterOfBinaryTree_1(root);
        //return diameterOfBinaryTree_2(root);
        /*diameterOfBinaryTree_3(root);
        return maxDia;*/

        int[] ans = new int[1];
        diameterOfBinaryTree_4(root, ans);
        return ans[0];
    }

    //LeetCode 112 - nodeToleaf
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        
        if(root.left == null && root.right == null)
            return (targetSum - root.val == 0);
        
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //LeetCode 113 - nodeToleaf
    public void pathSum_(TreeNode root, int target, List<List<Integer>> res, List<Integer> tempAns) {
        if(root == null)
            return;
        
        if(root.left == null && root.right == null){
            if(target - root.val == 0){
                ArrayList<Integer> base = new ArrayList<>(tempAns);
                base.add(root.val);
                res.add(base);
            }
            return;
        }
        
        tempAns.add(root.val);
        
        pathSum_(root.left, target - root.val, res, tempAns);
        pathSum_(root.right, target - root.val, res, tempAns);
        
        tempAns.remove(tempAns.size() - 1);
        
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSum_(root, targetSum, res, new ArrayList<>());
        return res;
    }

    //Maximum Path Sum between 2 Leaf Nodes(GfG)
    int maxLeafToLeafSum = -(int)1e9;
    int maxPathSum_(Node root)
    { 
        if(root == null) 
            return -(int)1e9;
            
        if(root.left == null && root.right == null)
            return root.data;
        
        int leftNodeToLeafSum = maxPathSum_(root.left);
        int rightNodeToLeafSum = maxPathSum_(root.right);
        
        if(root.left != null && root.right != null)
            maxLeafToLeafSum = Math.max(maxLeafToLeafSum, leftNodeToLeafSum + root.data + rightNodeToLeafSum);
        
        return Math.max(leftNodeToLeafSum, rightNodeToLeafSum) + root.data;
    } 
    
    int maxPathSum(Node root){
        if(root == null)
            return Integer.MIN_VALUE;
            
        maxPathSum_(root);
        return maxLeafToLeafSum;
    }
    
    //LeetCode 124 -  Binary Tree Maximum Path Sum

    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        
        /*maxPathSum_01(root);
        return maxNTN;*/

        return maxPathSum_02(root).NTNsum;
    }

    int maxNTN = -(int)1e9;
    public int maxPathSum_01(TreeNode root) {
        if(root == null)
            return 0;
        
        int leftMaxPathSum = maxPathSum_01(root.left);
        int rightMaxPathSum = maxPathSum_01(root.right);
        
        int maxSumTillRoot = Math.max(leftMaxPathSum, rightMaxPathSum) + root.val;
        maxNTN = Math.max(Math.max(maxNTN, maxSumTillRoot), Math.max(root.val, leftMaxPathSum + root.val + rightMaxPathSum));
        
        return Math.max(maxSumTillRoot, root.val);
    }


    public static class maxNTNpair{ //Max Node to Node Pair
        int NTNsum = -(int)1e9;
        int NTRsum = 0;

        maxNTNpair(int NTNsum, int NTRsum){
            this.NTNsum = NTNsum; //Node To Node Sum
            this.NTRsum = NTRsum; //Node to Root Sum
        }

        maxNTNpair(){

        }
    }

    public int maxValue(int... arr){
        int max = -(int)1e9;
        for(int ele : arr){
            max = Math.max(max, ele);
        }
        
        return max;
    }

    // {nodeToNode, nodeToRoot}
    public maxNTNpair maxPathSum_02(TreeNode node){
        if(node == null)
            return new maxNTNpair();

        maxNTNpair lpair = maxPathSum_02(node.left);
        maxNTNpair rpair = maxPathSum_02(node.right);

        maxNTNpair ans = new maxNTNpair();

        ans.NTNsum = maxValue(lpair.NTNsum, rpair.NTNsum, lpair.NTRsum + node.val, rpair.NTRsum + node.val, node.val, lpair.NTRsum + node.val + rpair.NTRsum);
        ans.NTRsum = maxValue(lpair.NTRsum + node.val, rpair.NTRsum + node.val, node.val);

        return ans;
    }
    
    //Leetcode 98 - Valid BST
    public class BSTpair{
        boolean isBST = true;
        long max = -(long)1e13;
        long min = (long)1e13;

        BSTpair(boolean isBST, long max, long min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }

        BSTpair(){

        }
    }

    public BSTpair isValidBST_01(TreeNode root) {
        if(node == null){
            return new BSTpair();
        }

        BSTpair lans = isValidBST_01(root.left);
        BSTpair rans = isValidBST_01(root.right);

        BSTpair ans = new BSTpair();

        ans.isBST = lans.isBST && rans.isBST && lans.max < root.val && root.val < rans.min;
        if(!ans.isBST)
            return false;

        ans.max = Math.max(rans.max, root.val);
        ans.min = Math.min(lans.min, root.val);

        return ans;
    }

    public boolean isValidBST(TreeNode root){
        return isValidBST_01(root).isBST;
    }

    long prevBSTnode = -(long) 1e13;
    public boolean isValidBST_02(TreeNode root){
        if(root == null)
            return true;

        if(!isValidBST_02(root.left))
            return false;

        if(root.val <= prevBSTnode)
            return false;

        prevBSTnode = root.val;

        if(!isValidBST_02(root.right))
            return false;

        return true;
    }

    //Leetcode 99 - Recover BST
    TreeNode a = null, b = null, prev = null;

    public boolean recoverTree_(TreeNode root) {

        if (root == null)
            return true;

        if (!recoverTree_(root.left))
            return false;

        if (prev != null && prev.val > root.val) {
            b = root;
            if (a == null)
                a = prev;
            else
                return false;
        }

        prev = root;
        if (!recoverTree_(root.right))
            return false;

        return true;

    }

    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
