package Tree;
import java.util.*;

public class GTree {
    public static class Node{
        int val = 0;
        ArrayList<Node> childs;

        Node(int val){
            this.val = val;
            this.childs = new ArrayList<>();
        }
    }

    public static int size(Node node){
        // if(node == null)
        //     return 0;

        int size = 0;
        for(Node child : node.childs){
            size += size(child);
        }

        return size + 1;
    }

    public static int height(Node node){
        int h = -1;
        for(Node child : node.childs){
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node node){
        int maxEle = node.val;
        for(Node child : node.childs){
            maxEle = Math.max(maxEle, maximum(child));
        }

        return maxEle;
    }

    public static boolean find(Node node, int data){
        if(node.val == data)
            return true;

        for(Node child : node.childs){
            if(find(child, data))
                return true;
        }

        return false;
    }

    public static int find01(Node node, int data){
        if(node.val == data)
            return 0;
        
        int depth = -1;

        for(Node child : node.childs){
            depth = find01(child, data);
            if(depth != -1)
                break;
        }

        if(depth != -1)
            depth++;

        return depth;
    }

    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans){
        if(root.val == data){
            ans.add(root);
            return true;
        }

        boolean res = false;
        for(Node child : root.childs){
            res = res || rootToNodePath(child, data, ans);
            // if(res) break;
        }

        if(res)
            ans.add(root);

        return res;
    }

    public static ArrayList<Node> rootToNodePath(Node root, int data){
        ArrayList<Node> ans = new ArrayList<>();
        rootToNodePath(root, data, ans);

        return ans;
    }

    public static Node LCA(Node node, int d1, int d2){
        ArrayList<Node> l1 = new ArrayList<>();
        rootToNodePath(node, d1, l1);

        ArrayList<Node> l2 = new ArrayList<>();
        rootToNodePath(node, d2, l2);

        Node LCA = null;
        int i = l1.size() - 1;
        int j = l2.size() - 1;

        while(i >= 0 && j >= 0){
            if(l1.get(i) != l2.get(j))
                break;

            LCA = l1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public void kDown(Node node, Node blockNode, int time, List<List<Integer>> ans){    //Here  K = time
        if(node == blockNode)
            return;

        if(ans.size() == time)
            ans.add(new ArrayList<>());

        ans.get(time).add(node.val);
        for(Node child : node.childs){
            kDown(child, blockNode, time + 1, ans);
        }
    }

    //Method 01 - using rootToNodePath
    public List<List<Integer>> burningTree_01(Node node, int target){    //burningTree same as distanceK
        ArrayList<Node> list = new ArrayList<>();
        rootToNodePath(node, target, list);

        List<List<Integer>> ans = new ArrayList<>();
        Node blockNode = null;

        for(int i = 0; i < list.size(); i++){
            kDown(list.get(i), blockNode, i, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    //Method 02 - without using rootToNodePath
    public int burningTree_02(Node node, int target, List<List<Integer>> ans){    //burningTree same as distanceK
        if(node.val == target){
            kDown(node, null, 0, ans);
            return 1;
        }

        int time = -1;
        Node blockNode = null;
        for(Node child : node.childs){
            time = burningTree_02(child, target, ans);
            if(time != -1){
                blockNode = child;
                break;
            }
        }

        if(time != -1){
            kDown(node, blockNode, time, ans);
            time++;
        }

        return time;
    }
}
