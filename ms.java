import java.util.*;
public class MergeSort{

    static void merge(int[] arr,int low,int mid,int high){
        ArrayList<Integer> temp=new ArrayList<>();
        int left=low;
        int right=mid+1;

        while(left<=mid && right<=high){
            if(arr[left]<arr[right]){
                temp.add(arr[left]);
                left++;
            }
            else{
                temp.add(arr[right]);
                right++;
            }
        }
        while(left<=mid){
            temp.add(arr[left]);
            left++;
        }
        while(right<=high){
            temp.add(arr[right]);
            right++;
        }

        for(int i=low;i<=high;i++){
            arr[i]=temp.get(i-low);
        }
    }
    static void mergesort(int[] arr,int low,int high){
        if(low>=high)
        return;
    int mid=(low+high)/2;
    mergesort(arr, low, mid);
    mergesort(arr, mid+1, high);
    merge(arr,low,mid,high);
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
        MergeSort.mergesort(arr, 0, n-1);
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