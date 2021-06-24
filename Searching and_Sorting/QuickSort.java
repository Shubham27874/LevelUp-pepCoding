public class QuickSort {
    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }
    
    public static int segregateData(int[] arr, int si, int ei, int pivot){
        swap(arr[pivot], arr[ei]);
        int p = si - 1, itr = si;
        while (itr <= ei){
            if (arr[itr] <= arr[ei])
                swap(arr[++p], arr[itr]);
            itr++;
        }
        return p;
    }

    public static void quickSort(int[] arr, int si, int ei){
        if (si > ei)
            return;

        // int len = (ei - si + 1);
        // int pivot = rand() % len + si;

        int pivot = ei;
        int pIdx = segregateData(arr, si, ei, pivot);

        //Sorted Array Check
        // if it is sorted then return

        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }

    public static void main(String[] args){
        int[] arr = {-12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3};
        quickSort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.println(ele + " ");;
    }
}
