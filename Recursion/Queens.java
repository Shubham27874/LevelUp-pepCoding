package Recursion;

import java.util.*;

public class Queens {
    // tnb = total no. of boxes
    // bno = box no.
    // tnq = total no. of queens
    // qpsf = queen placed so far
    public static int queenCombination1D(int tnb, int bno, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int c = 0;
        for (int i = bno; i < tnb; i++) {
            c += queenCombination1D(tnb, i + 1, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
        }

        return c;
    }

    public static int queenPermutaion1D(boolean[] tnb, int tnq, int qpsf, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }

        int c = 0;
        for (int i = 0; i < tnb.length; i++) {
            if (!tnb[i]) {
                tnb[i] = true;
                c += queenPermutaion1D(tnb, tnq, qpsf + 1, ans + "b" + i + "q" + qpsf + " ");
                tnb[i] = false;
            }
        }
        return c;

    }

    public static int queenCombination2D(boolean[][] tnb, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tnb.length;
        int m = tnb[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = idx / m;
            int c = idx % m;

            c += queenCombination2D(tnb, i + 1, tnq - 1, ans + "(" + r + "," + c + ")");
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] tnb, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = tnb.length;
        int m = tnb[0].length;
        for (int i = 0; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!tnb[r][c]) {
                tnb[r][c] = true;
                count += queenPermutation2D(tnb, tnq - 1, ans + "(" + r + "," + c + ") ");
                tnb[r][c] = false;
            }
        }

        return count;
    }
    // nQueen_Problems--------------------------------------------------------------

    public static boolean isSafeToPlace(boolean[][] boxes, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = d; rad < boxes.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if (x >= 0 && y <= 0 && x < boxes.length && y < boxes[0].length) {
                    if (boxes[x][y]) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }

    // Method 1 (using isSafe boolean array to check the safe place)
    public static int Queen01(boolean[][] boxes, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxes.length;
        int m = boxes[0].length;
        for (int i = idx; i < n * m; i++) {
            int r = idx / m;
            int c = idx % m;
            if (isSafeToPlace(boxes, r, c)) {
                c += Queen01(boxes, idx + 1, tnq - 1, ans + "(" + r + "," + c + ")");
            }
        }

        return count;
    }

    // Method 2 - branch and bound(toggle karke)
    static boolean[] row;
    static boolean[] col;
    static boolean[] Diag;
    static boolean[] aDiag;

    public static void toggleNqueen(int r, int c, int n) {
        row[r] = !row[r];
        col[c] = !col[c];
        Diag[r - c + n - 1] = !Diag[r - c + n - 1];
        aDiag[r + c] = !aDiag[r + c];
    }

    public static int Queen02(int n, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (!row[r] && !col[c] && !Diag[r - c + n - 1] && !aDiag[r + c]) {
                toggleNqueen(r, c, n);
                count += Queen02(n, i + 1, tnq - 1, ans + "(" + r + "," + c + ")");
                toggleNqueen(r, c, n);
            }
        }

        return count;
    }

    public static int nQueen02_Perm(boolean[][] boxes, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = boxes.length;
        for (int i = 0; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (!boxes[r][c] && !row[r] && !col[c] && !Diag[r - c + n - 1] && !aDiag[r + c]) {
                toggleNqueen(r, c, n);
                boxes[r][c] = true;

                count += nQueen02_Perm(boxes, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNqueen(r, c, n);
                boxes[r][c] = false;
            }
        }
        return count;
    }

    // Method 3 - Room-Floor technique
    public static int Queen03(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if (!row[r] && !col[c] && !Diag[r - c + n - 1] && !aDiag[r + c]) {
                toggleNqueen(r, c, n);
                count += Queen02(n, c + 1, tnq - 1, ans + "(" + r + "," + c + ")");
                toggleNqueen(r, c, n);
            }
        }

        return count;
    }

    static int colB = 0;
    static int diagB = 0;
    static int aDiagB = 0;

    public static void toggleNQueenBit(int r, int c, int n) {
        colB ^= (1 << c);
        diagB ^= (1 << (r - c + n - 1));
        aDiagB ^= (1 << (r + c));
    }

    public static int nQueen04BitMask(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if ((colB & (1 << c)) == 0 && (diagB & (1 << (r - c + n - 1))) == 0 && (aDiagB & (1 << (r + c))) == 0) {
                toggleNQueenBit(r, c, n);
                count += nQueen04BitMask(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueenBit(r, c, n);
            }
        }

        return count;
    }

    public static void main(String args[]) {
        // System.out.println(queenCombination1D(5, 0, 3, 0, ""));
        // boolean[] vis = new boolean[5];
        // System.out.println(queenPermutaion1D(vis, 3, 0, ""));

        int n = 4;
        // boolean[][] tnb = new boolean[n][n];
        row = new boolean[n];
        col = new boolean[n];
        Diag = new boolean[n + n - 1];
        aDiag = new boolean[n + n - 1];
        System.out.println(Queen02(n, 0, n, ""));
    }
}