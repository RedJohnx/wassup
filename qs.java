import java.util.*;
public class QuickSort {
    static int partition(int[] arr,int low,int high){
        int pivot=arr[low];
        int i=low;
        int j=high;

        while(i<j){
            while(arr[i]<=pivot && i<=high-1){
                i++;
            }
            while(arr[j]>=pivot && j>=low+1){
                j--;
            }
            if(i<j){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        int temp=arr[low];
        arr[low]=arr[j];
        arr[j]=temp;
        return j;
    }
    static void qs(int[] arr,int low,int high){
        if(low<high){
            int pIndex=partition(arr, low, high);
            qs(arr, low, pIndex-1);
            qs(arr, pIndex+1, high);
        }
    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the numebr of elements to be sorted");
        int n=s.nextInt();
        int arr[]=new int[n];
        System.out.println("Enter the array elements");
        for(int i=0;i<n;i++){
         arr[i]=s.nextInt();
        }
        System.out.println("Before sorting array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        long start=System.currentTimeMillis();
        QuickSort.qs(arr, 0, n-1);
        long end=System.currentTimeMillis();
        System.out.println();
        System.out.println("After sorting array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("Time Elapsed "+(end-start));
        s.close();
    }
}