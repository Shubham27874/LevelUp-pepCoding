public class l002 {
    public static int hamiltonianCycleandPath(int src, int osrc, boolean[] vis, String psf, int totalNoedges){
        if(totalNoedges == N - 1){
            int idx = findEdge(osrc, src);
            if(idx != -1)
                System.out.println("Cycle: " + psf + src);
            else
                System.out.println("Path: " + psf + src);
                System.out.println();
        }
        
        int count = 0;
        vis[scr] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                count += hamiltonianCycleandPath(e.v, osrc, vis, psf + src + " ", totalNoedges + 1);
            }
        }
        vis[src] = false;
        return count;
    }

    public static void hamiltonianCycleandPath(int src){
        boolean[] vis = new boolean[N];
        hamiltonianCycleandPath(0, 0, vis, " ", 0);
    }   

    public static void dfs(int src, boolean[] vis, List<Integer> ans){ 
        
        vis[src] = true;

        for(Edge e : graph[src]){
            if(!vis[e.v])
                dfs(e.v, vis, ans);
        }

        vis[src] = false;

        ans.add(src);
    }

    public static int gcc(){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayLsit<>();
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

    public static void main(String[] args){
        hamiltonianCycleandPath(0);
    }
    
}
