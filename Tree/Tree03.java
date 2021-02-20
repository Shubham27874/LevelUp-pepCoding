import java.util.LinkedList;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Tree03 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelOrderSimple(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();

        que.addLast(root);

        while(que.size() != 0){
            TreeNode rn = que.removeFirst();

            System.out.println(rn.val + " ");

            if(rn.left != null)
                que.addLast(rn.left);

            if(rn.right != null)
                que.addLast(rn.right);    
        }
    }

    public static void levelOrderLineWise_01(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> childque = new LinkedList<>();

        que.addLast(root);
        int level = 0;
        System.out.println("Level" + level + " : ");

        while(que.size() != 0){
            TreeNode rn = que.removeFirst();
            System.out.println(rn.val + " ");

            if(rn.left != null)
                childque.addLast(rn.left);

            if(rn.right != null)
                childque.addLast(rn.right);    
            
            if(que.size() == 0) {
                System.out.println();
                if(que.size() != 0) System.out.println("Level" + (++level) + " : ");

                LinkedList<TreeNode> temp = que;
                que = childque;
                childque = temp;
            }
        }
    }

    public static void levelOrderLineWise_02(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();

        que.addLast(root);
        que.addLast(null);

        int level = 0;
        System.out.println("Level" + level + " : ");

        while(que.size != 1){
            TreeNode rn = que.removeFirst();
            System.out.println(rn.val + " ");

            if(rn.left != null)
                que.addLast(rn.left);
            
            if(rn.right != null)
                que.addLast(rn.right);

            if(que.getFirst() == null){
                System.out.println();
                System.out.println("Level" + (++level) + " : ");
                que.addLast(que.removeFirst()); // null aage add kardiya
            }
        }
    }

    

    public static void main(String[] args){

    }
}
