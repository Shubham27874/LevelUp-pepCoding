package Graphs;
import java.util.*;

public class l002 {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;

    @SuppressWarnings("unchecked")
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static int findEdge(int u, int v){
        int idx = -1;

        for(int i = 0; i < graph[u].size(); i++){
            if(graph[u].get(i).v == v){
                idx = i;
                break;
            }
        }

        return idx;    //will return the value of "v" for the given "u";
    }

    public static int hamiltonianCycleandPath(int src, int osrc, boolean[] vis, String psf, int totalNoedges){
        if(totalNoedges == N - 1) {
            int idx = findEdge(src, osrc);
            if(idx != -1)
                System.out.print("Cycle" + psf + src + " ");
            else
                System.out.print("Path" + psf + src + " ");

            System.out.println();
            return 1;
        }

        vis[src] = true;
        int count = 0;
        for(Edge e : graph[src]){
            if(!vis[e.v])
                count += hamiltonianCycleandPath(e.v, osrc, vis, psf + src, totalNoedges + 1);
        }

        vis[src] = false;
        return count;
    }

    public static void hamiltonianCycleandPath(int src){
        boolean[] vis = new boolean[N];
        hamiltonianCycleandPath(src, src, vis, " ", 0);
    }   

    public static void dfs(int src, boolean[] vis, ArrayList<Integer> ans){ 
        
        vis[src] = true;

        for(Edge e : graph[src]){
            if(!vis[e.v])
                dfs(e.v, vis, ans);
        }

        vis[src] = false;
        ans.add(src);
    }

    public static int gcc(){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[N];
        int components = 0;

        for(int i = 0; i < N; i++){
            if(!vis[i]){
                dfs(i, vis, ans);
                res.add(ans);
                components++;
            }
        }

        return components;
    }   

    //LeetCode 200 - NumberOfIslands 
    public void dfsIsland(int i, int j, int n, int m, boolean[] vis, char[][] grid, int[][] dir){
        grid[i][j] = '0';

        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1'){
                dfsIsland(r, c, n, m, vis, grid, dir);
            }
        }
    }

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[] vis = new boolean[Math.max(n, m)];
        int count = 0;
        
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    count++;
                    dfsIsland(i, j, n, m, vis, grid, dir);
                }
            }
        }

        return count;
    }

    //LeetCode 695 - Max Area of Island
    public int dfsArea(int i, int j, int n, int m, boolean[] vis, int[][] grid, int[][] dir){
        grid[i][j] = 0;
        int area = 1;
        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                area += dfsArea(r, c, n, m, vis, grid, dir);
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[] vis = new boolean[Math.max(n, m)];
        //int count = 0;
        
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int Area = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    Area = Math.max(Area, dfsArea(i, j, n, m, vis, grid, dir));
                }
            }
        }

        return Area;
    }
    
    //LeetCode 463 - Island Perimeter
    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0)
            return 0;

        int nbrs = 0;
        int count = 0;
        
        int n = grid.length;
        int m = grid[0].length;

        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    count++;
                    for(int d = 0; d < dir.length; d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                            nbrs++;
                    }
                }
            }
        }

        return count * 4 - nbrs;
    }

    //LeeCode 130 - Surrounded Regions
    public void surroundigsRegionsDFS(int i, int j, int n, int m, char[][] grid, int[][] dir){
        grid[i][j] = '#';
        for(int d = 0; d < dir.length; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
                surroundigsRegionsDFS(r, c, n, m, grid, dir);
        }
    }

    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length == 0)
            return;

        int n = board.length;
        int m = board[0].length;

        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O')
                surroundigsRegionsDFS(i, 0, n, m, board, dir);
            
            if(board[i][m - 1] == 'O')
                surroundigsRegionsDFS(i, m - 1, n, m, board, dir);
        }

        for(int i = 0; i < m; i++){
            if(board[0][i] == 'O')
                surroundigsRegionsDFS(0, i, n, m, board, dir);
            
            if(board[n - 1][i] == 'O')
                surroundigsRegionsDFS(n -1, i, n, m, board, dir);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                
                if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    //Leetcode 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = n;
        
        if(n < 0 || m < 0)
            return -1;

        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;

        LinkedList<Integer> que = new LinkedList<>();

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        grid[0][0] = 1;
        que.push(0);

        int level = 1;
        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int idx = que.removeFirst();

                int r = idx / m;
                int c = idx % m;

                if(r == n - 1 && c == m - 1){
                    return level;
                }

                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 0){
                        grid[x][y] = 1;
                        que.addLast(x * m + y);
                    }
                }
            }

            level++;
        }

        return -1;
    }

    //Leetcode 785
    public boolean isBipartite_(int[][] graph, int[] vis, int src) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0;  // 0 : red, 1 : green
        boolean isCycle = false;

        while(que.size() != 0){
            int sz = que.size();
            while(sz-- > 0){
                int rvtx = que.removeFirst();

                if(vis[rvtx] != -1){
                    isCycle = true;
                    if(vis[rvtx] != color) return false;

                    continue;
                }

                vis[rvtx] = color;
                for(int v : graph[rvtx]){
                    if(vis[v] == -1)
                        que.addLast(v);
                }
            }
            color = (color + 1) % 2;
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // -1 : not visited, 0 : red, 1 : green
        int[] vis = new int[n];
        Arrays.fill(vis, -1);

        boolean res = true;
        for(int i = 0; i < n; i++){
            if(vis[i] == -1){
                res = res && isBipartite_(graph, vis, i);
            }
        }

        return res;
    }

    public static void main(String[] args){
        hamiltonianCycleandPath(0);
    }
    
}
