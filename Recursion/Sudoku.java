package Recursion;

import java.util.*;
//import java.lang.*;

public class Sudoku {
    // Sudoku -
    // 1--------------------x-------------------------------------x---------------------x-------------------------------x------------
    static int[][] board = { { 3, 0, 0, 0, 0, 0, 0, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

    
    public static void display(){
        for (int[] ar : board) 
        {
            for (int ele : ar) 
            {
                System.out.println(ele + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafeToplace(int r, int c, int num) {
        // Row
        for (int i = 0; i < board[0].length; i++) {
            if (board[r][i] == num)
                return false;
        }

        // Column
        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == num)
                return false;
        }

        // 3x3 Compressed Matrix
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + r][j + c] == num)
                    return false;
            }
        }

        return true;
    }

    public static boolean sudokuSolver01(int idx){
        if(idx == board.length * board[0].length){
            display();
            return true;
        }

        int r = idx / board[0].length;
        int c = idx % board[0].length;
        boolean res = false;
        //int count = 0;

        if(board[r][c] != 0){
            return sudokuSolver01(idx + 1);
        }

        for(int num = 1; num <= 9; num++){
            if(isSafeToplace(r, c, num)){
                board[r][c] = num;
                res = res || sudokuSolver01(idx + 1);
                board[r][c] = 0;
            }
        }

        return res;
    }

    public static boolean sudokuSolver02(ArrayList<Integer> loc, int idx){
        if(idx == loc.size()){
            display();
            return true;
        }

        int r = loc.get(idx) / board[0].length;
        int c = loc.get(idx) % board[0].length;
        boolean res = false;
        //int count = 0;

        /*if(board[r][c] != 0){
            return sudokuSolver01(idx + 1);
        }*/

        for(int num = 1; num <= 9; num++)
        {
            if(isSafeToplace(r, c, num))
            {
                board[r][c] = num;
                res = res || sudokuSolver01(idx + 1);
                board[r][c] = 0;
            }
        }

        return res;
    }



    //WordBreak - 1----------------------------x-----------------------------x------------------------------------x-----------------x---
    public static void wordBreak(String str, String ans, HashSet < String > dict) {
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }
        
        for(int i = 1; i <= str.length(); i++){
            String word = str.substring(0,i);
            
            if(dict.contains(word)){
                wordBreak(str.substring(i), ans + word + " ", dict);
            }}
        }
    
    //CrossWord Puzzle - 1 ----------------x------------------------------x----------------------x-------------------------------------
    public static boolean isPossibletoPlaceH(char[][] arr, int r, int c, String word){
        for(int i = 0; i < word.length(); i++){
            if(c + i >= arr[0].length){
                return false;
            }

            if( arr[r][c + i] != '-' && arr[r][c + i] != word.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeH(char[][] arr, int r, int c, String word){
        boolean[] charLoc = new boolean[26]; // jo word maine place kiye hai unke liye, taki agar poland ke 'l' pe lhasa place krne gaye aur unplace karte waqt 'l' unplace na karde poland se.
        for(int i = 0; i < word.length(); i++){
            if(arr[r][c + i] == '-'){
                charLoc[i] = true;
                arr[r][c + i] = word.charAt(i);
            }
        }

        return charLoc;
    }

    public static void unplaceH(char[][] arr, int r, int c, String word, boolean[] charLoc){
        for(int i = 0; i < word.length(); i++){
            if(charLoc[i]){
                arr[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossibletoPlaceV(char[][] arr, int r, int c, String word){
        for(int i = 0; i < word.length(); i++){
            if(r + i >= arr[0].length){
                return false;
            }

            if( arr[r + i][c] != '-' && arr[r + i][c] != word.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeV(char[][] arr, int r, int c, String word){
        boolean[] charLoc = new boolean[26];
        for(int i = 0; i < word.length(); i++){
            if(arr[r + i][c] == '-'){
                charLoc[i] = true;
                arr[r + i][c] = word.charAt(i);
            }
        }

        return charLoc;
    }

    public static void unplaceV(char[][] arr, int r, int c, String word, boolean[] charLoc){
        for(int i = 0; i < word.length(); i++){
            if(charLoc[i]){
                arr[r + i][c] = '-';
            }
        }
    }

    public static void displayCharArray(char[][] arr) {
        for (char[] ar : arr) {
            for (char ele : ar) {
                System.out.println(ele + " ");
            }
            System.out.println();
        }

    }
    public static int crossWord(char[][] arr, String[] words, int vidx){
        if(vidx == words.length){
            displayCharArray(arr);
            return 1;
        }

        int count = 0;

        String word = words[vidx];

        for(int r = 0; r < arr.length; r++){
            for(int c = 0; c < arr[0].length; c++){
                if(arr[r][c] == '-' || arr[r][c] == word.charAt(0)){

                    if(isPossibletoPlaceH(arr,r, c, word)){
                        boolean[] charLoc = placeH(arr, r, c, word);
                        count += crossWord(arr, words, vidx + 1);
                        unplaceH(arr, r, c, word, charLoc);
                    }

                    if(isPossibletoPlaceV(arr, r, c, word)){
                        boolean[] charLoc = placeV(arr, r, c, word);
                        count += crossWord(arr, words, vidx + 1);
                        unplaceV(arr, r, c, word, charLoc);
                    }
                }
            }
        }

        return count;
    }

    public static void main(String args[]){
        //System.out.println(sudokuSolver01(0));
        
        int n = board.length;
        int m = board[0].length;
        ArrayList<Integer> loc = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 0)
                loc.add(i * m + j);
            }
        }

        System.out.println(sudokuSolver02(loc, 0));

    }

}
