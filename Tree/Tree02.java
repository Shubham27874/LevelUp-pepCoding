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


    public int diameterOfBinaryTree(TreeNode root){
        if(root == null)
            return 0;
        
        //return diameterOfBinaryTree_1(root);
        return diameterOfBinaryTree_2(root);
    }
}
