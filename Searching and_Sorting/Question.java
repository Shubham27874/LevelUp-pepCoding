public class Question {
    //Leetcdoe 704
    public static int binarySearch(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data)
                return mid;
            else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int firstIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid - 1 >= 0 && arr[mid - 1] == data)
                    ei = mid - 1;
                else  
                    return mid;
            } else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int lastIndex(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid + 1 < n && arr[mid + 1] == data)
                    si = mid + 1;
                else  
                    return mid;
            } else if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return -1;
    }

    public static int firstIndex_01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] < data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        return arr[si] == data ? si : -1;
    }

    public static int lastIndex_01(int[] arr, int data) {
        int n = arr.length, si = 0, ei = n - 1;

        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
        }

        si--;
        return si >= 0 && arr[si] == data ? si  : -1;
    }

    //Leetcode 34
    public int[] searchRange(int[] arr, int data) {
        return new int[] {firstIndex(arr, data), lastIndex(arr, data)};
    }

    public int insertLocation(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei){
            int mid = (si + ei) / 2;
            if (arr[mid] <= data)
                si = mid + 1;
            else
                ei = mid - 1;
            }

        return si;
    }

    int perfectPosOfElement(int[] arr, int data){
        int insertPos = insertLocation(arr, data);
        int lastIndex = insertPos - 1;

        return (lastIndex >= 0 && arr[lastIndex] == data) ? lastIndex : insertPos;
    }


    int nearestElement(int[] arr, int data){
        if(arr.length == 0)
            return 0;

        int n = arr.length;
        if(data <= arr[0] || data >= arr[n - 1])
            return data <= arr[0] ? arr[0] : arr[n - 1];

        int si = 0, ei = n - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] <= data)
                si = mid + 1;
            else    
                ei = mid - 1;
        }

        return data - arr[ei] <= arr[si] - data ? arr[ei] : arr[si];
    }
}
