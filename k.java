import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = sc.nextInt();

        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w]; 
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }

        System.out.println("Maximum value achievable: " + dp[n][capacity]);

        List<Integer> chosenItems = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                chosenItems.add(i - 1);
                w -= weights[i - 1];
            }
        }

        Collections.reverse(chosenItems);

        System.out.println("Items chosen (0-indexed):");
        for (int idx : chosenItems) {
            System.out.println("Item " + idx + " -> Weight: " + weights[idx] + ", Value: " + values[idx]);
        }

        sc.close();
    }
}