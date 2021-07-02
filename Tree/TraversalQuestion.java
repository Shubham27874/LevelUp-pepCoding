package Tree;
import java.util.*;

public class TraversalQuestion {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { 
            this.val = val; 
        }
    }

    class Node{
        int data;
        Node left, right;

        Node(int item){
            data = item;
            left = right = null;
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
    
    public static void levelOrderlinewise_01(TreeNode root){ //using two queues
        LinkedList<TreeNode> que = new LinkedList<>();
        LinkedList<TreeNode> childQue = new LinkedList<>();
        
        que.addLast(root);
        int level = 0;
        System.out.print("Level" + " " + level + ":");
        while(que.size() != 0){
            TreeNode rn = que.removeFirst();
            System.out.print(rn.val + " ");

            if(rn.left != null)
                childQue.addLast(rn.left);

            if(rn.right != null)
                childQue.addLast(rn.right);

            if(que.size() == 0){
                System.out.println();
                if(childQue.size() != 0)
                    System.out.print("Level" + " " + (++level) + ":");

                LinkedList<TreeNode> temp = que;
                que = childQue;
                childQue = temp;
            }
        }
    }

    public static void levelOrderlinewise_02(TreeNode root){   //using null
        LinkedList<TreeNode> que = new LinkedList<>();
        
        que.addLast(root);
        que.addLast(null);
        int level = 0;

        System.out.print("Level" + level + " ");
        while(que.size() != 1){
            TreeNode rn = que.removeFirst();
            System.out.println(rn.val + " ");

            if(rn.left != null)
                que.addLast(rn.left);

            if(rn.right != null)
                que.addLast(rn.right);

            if(que.getFirst() == null){
                System.out.println();
                System.out.print("Level" + " " + (++level) + ":");
                que.addLast(que.removeFirst());
            }
        }
    }

    public static void levelOrderlinewise_03(TreeNode root){   
        LinkedList<TreeNode> que = new LinkedList<>();
        
        que.addLast(root);

        int level = 0;
        System.out.print("Level" + level + " ");
        while(que.size() != 0){
            int size = que.size();
            System.out.print("Level" + level + " ");

            while(size-- > 0){
                TreeNode rn = que.removeFirst();
                System.out.println(rn.val + " ");

                if(rn.left != null)
                    que.addLast(rn.left);

                if(rn.right != null)
                    que.addLast(rn.right);                
            }

            level++;
            System.out.println();
        }
    }

    //Leetcode 102
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        LinkedList<TreeNode> que = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();

        que.addLast(root);
        int level = 0;

        while(que.size() != 0){
            int size = que.size();

            while(size-- > 0){
                TreeNode rn = que.removeFirst();

                if(level == ans.size())
                    ans.add(new ArrayList<>());
                ans.get(level).add(rn.val);

                if(rn.left != null)
                    que.addLast(rn.left);

                if(rn.right != null)
                    que.addLast(rn.right);
            }

            level++;
        }

        return ans;
    }

    public static void leftViewOfABinaryTree(TreeNode root){   
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            System.out.println(que.getFirst().val + " ");

            while(size-- > 0){
                TreeNode rn = que.removeFirst();

                if(rn.left != null)
                    que.addLast(rn.left);

                if(rn.right != null)
                    que.addLast(rn.right);                
            }

            level++;
            System.out.println();
        }
    }

    public static void rightViewOfABinaryTree(TreeNode root){   
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        int level = 0;
        while(que.size() != 0){
            int size = que.size();
            System.out.println(que.getFirst().val + " ");

            while(size-- > 0){
                TreeNode rn = que.removeFirst();

                if(rn.right != null)
                    que.addLast(rn.right);

                if(rn.left != null)
                    que.addLast(rn.left);            
            }

            level++;
            System.out.println();
        }
    }

    //GFG - leftViewOfABinaryTree
    ArrayList<Integer> leftView(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null)
            return new ArrayList<>();
            
        
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() != 0){
            int size = que.size();
            // System.out.println(que.getFirst().data + " ");
            ans.add(que.getFirst().data);

            while(size-- > 0){
                Node rn = que.removeFirst();

                if(rn.left != null)
                    que.addLast(rn.left);

                if(rn.right != null)
                    que.addLast(rn.right);                
            }
        }

        return ans;
    }

    //GFG - rightViewOfABinaryTree
    //Leetcode 199
    ArrayList<Integer> rightView(Node node) {
        if(node == null)
            return new ArrayList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while(que.size() != 0){
            int size = que.size();
            // System.out.println(que.getFirst().data + " ");
            ans.add(que.getFirst().data);

            while(size-- > 0){
                Node rn = que.removeFirst();

                if(rn.right != null)
                    que.addLast(rn.right);

                if(rn.left != null)
                    que.addLast(rn.left);            
            }
        }

        return ans;
    }

    public static class verticalPair{
        TreeNode node = null;
        int hl = 0;  //horizontal level

        verticalPair(TreeNode node, int hl){
            this.node = node;
            this.hl = hl;
        }
    }

    public static List<List<Integer>> verticalOrderTraversal(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, 0));
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        int minHL = 0;
        int maxHL = 0;

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                verticalPair rp = que.removeFirst();

                map.putIfAbsent(rp.hl, new ArrayList<>());
                //if(!map.conatinsKey(rp.h1))
                    // map.put(rp.hl, new ArrayList><());

                minHL = Math.min(minHL, rp.hl);
                maxHL = Math.max(maxHL, rp.hl);

                map.get(rp.hl).add(rp.node.val);
            
                if(rp.node.left != null)
                    que.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    que.add(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while(minHL <= maxHL){
            ans.add(map.get(minHL));
            minHL++;
        }

        return ans;
    }

    public static void width(TreeNode root, int hl, int[] ans){
        if(root == null)
            return;
        
        ans[0] = Math.min(hl, ans[0]);
        ans[1] = Math.max(hl, ans[1]);

        width(root.left, hl - 1, ans);
        width(root.right, hl + 1, ans);
    }

    public static List<List<Integer>> verticalOrderTraversal_02(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < length; i++){
            ans.add(new ArrayList<>());
        }
        
        que.addLast(new verticalPair(root, -minMax[0]));

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                verticalPair rp = que.removeFirst();

                ans.get(rp.hl).add(rp.node.val);

                if(rp.node.left != null)
                    que.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    que.add(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }

    public static int[] verticalSum(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        int[] ans = new int[length];
        
        que.addLast(new verticalPair(root, -minMax[0]));

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                verticalPair rp = que.removeFirst();

                ans[rp.hl] += rp.node.val;  // ans.set(rp.hl,ans.get(rp.hl) + rp.node.val);

                if(rp.node.left != null)
                    que.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    que.add(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }

    public static int[] bottomView(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        int[] ans = new int[length];
        
        que.addLast(new verticalPair(root, -minMax[0]));

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                verticalPair rp = que.removeFirst();

                ans[rp.hl] = rp.node.val;

                if(rp.node.left != null)
                    que.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    que.add(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }

    public static List<List<Integer>> diagonalOrderTraversal(TreeNode root){
        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, 0));
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        int minHL = 0;
        int maxHL = 0;

        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                verticalPair rp = que.removeFirst();

                map.putIfAbsent(rp.hl, new ArrayList<>());
                //if(!map.conatinsKey(rp.h1))
                    // map.put(rp.hl, new ArrayList><());

                map.get(rp.hl).add(rp.node.val);

                minHL = Math.min(minHL, rp.hl);
                maxHL = Math.max(maxHL, rp.hl);
            
                if(rp.node.left != null)
                    que.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    que.add(new verticalPair(rp.node.right, rp.hl));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while(minHL <= maxHL){
            ans.add(map.get(minHL));
            minHL++;
        }   

        return ans;
    }

    //Leetcode 987
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<verticalPair> que = new PriorityQueue<>((a,b) -> {
            return a.node.val - b.node.val;
        });

        PriorityQueue<verticalPair> childQue = new PriorityQueue<>((a,b) -> {
            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < length; i++){
            ans.add(new ArrayList<>());
        }
        
        que.add(new verticalPair(root, -minMax[0]));

        while(que.size() != 0){
                verticalPair rp = que.remove();

                ans.get(rp.hl).add(rp.node.val);

                if(rp.node.left != null)
                    childQue.add(new verticalPair(rp.node.left, rp.hl - 1));

                if(rp.node.right != null)
                    childQue.add(new verticalPair(rp.node.right, rp.hl + 1));

                if(que.size() == 0){
                    PriorityQueue<verticalPair> temp = que;
                    que = childQue;
                    childQue = temp;
                }
        }

        return ans;
    }

    //Another Approach
    public static class verticalPair2{
        TreeNode node = null;
        int x = 0;  //horizontal level
        int y = 0;  //vertical level

        verticalPair2(TreeNode node, int x, int y){
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> verticalTraversal_(TreeNode root) {
        PriorityQueue<verticalPair2> que = new PriorityQueue<>((a,b) -> {
            if(a.y != b.y)
                return a.y - b.y;
            else    
                return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < length; i++){
            ans.add(new ArrayList<>());
        }
        
        que.add(new verticalPair2(root, -minMax[0], 0));

        while(que.size() != 0){
                verticalPair2 rp = que.remove();

                ans.get(rp.x).add(rp.node.val);

                if(rp.node.left != null)
                    que.add(new verticalPair2(rp.node.left, rp.x - 1, rp.y + 1));

                if(rp.node.right != null)
                    que.add(new verticalPair2(rp.node.right, rp.x + 1, rp.y + 1));
        }

        return ans;
    }
}
