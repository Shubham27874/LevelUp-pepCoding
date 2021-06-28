import java.util.*;

public class MaxAreaOfCake {
    public static boolean isPossibleToServeCake(int[] radius, double cakeArea, int guest){
        int g = 0;
        for(int i = radius.length - 1; i >= 0; i--){
            double area = Math.PI * radius[i] * radius[i];
            g += Math.floor(area / cakeArea);
            if(g >= guest)
                return true;
        }

        return false;
    }   
    
    public static double maximumAreaOfCake(int[] radius, int guest){
        Arrays.sort(radius);
        int n = radius.length;
        double si = (Math.PI * radius[0] * radius[0]) / guest, ei = Math.PI * radius[n - 1] * radius[n - 1];
        // si = 0.0, ei = 1e7; -> if the array was not sorted\
        while((ei - si) > 1e7){
            double cakeArea = (si + ei) / 2.0;
            if(!isPossibleToServeCake(radius, cakeArea, guest))
                ei = cakeArea - 1e-5;
            else
                si = cakeArea;
        }

        return si;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        System.out.println(maximumAreaOfCake(arr, 6));
    }
}
