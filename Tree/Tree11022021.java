public static class Tree11022021 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class solPair {
        TreeNode prev = null;
        TreeNode pred = null;
        TreeNode succ = null;
    }

    //pred - succ in binaryTree
    public static void allSolution(TreeNode node, int data, solPair pair){
        if(node == null)
            return;

        allSolution(node.left, data, pair);
        if(node.val == data && pair.prev == null)
            pair.pred = pair.prev;
        if(pair.prev.val == data && pair.succ == null)
            pair.succ = node;

        pair.prev = node;
        allSolution(node.right, data, pair);
    }
    
}