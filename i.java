import java.util.*;

public class Inversion {
    static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        int invCount = 0; 

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                invCount += (mid - left + 1); 
                right++;
            }
        } 
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return invCount;
    }
    static int mergesort(int[] arr, int low, int high) {
        int invCount = 0;
        if (low < high) {
            int mid = (low + high) / 2;
            invCount += mergesort(arr, low, mid);       
            invCount += mergesort(arr, mid + 1, high);  
            invCount += merge(arr, low, mid, high);     
        }
        return invCount;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of elements to be sorted:");
        int n = s.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println("Before sorting array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        long start = System.currentTimeMillis();
        int inversionCount = mergesort(arr, 0, n - 1);

        long end = System.currentTimeMillis();
        System.out.println("After sorting array:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("The number of inversions in the array is: " + inversionCount);
        System.out.println("Time elapsed: " + (end - start) + " ms");

        s.close();
    }
    
}