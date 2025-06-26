import java.util.*;

public class GaleShapley{

    static int N;

    // Function to check if woman 'w' prefers man 'm' over her current engagement 'm1'
    static boolean prefersNewProposal(int[][] womenPref, int w, int m, int m1) {
        for (int i = 0; i < N; i++) {
            if (womenPref[w][i] == m)
                return true;
            if (womenPref[w][i] == m1)
                return false;
        }
        return false;
    }

    static void stableMarriage(int[][] menPref, int[][] womenPref) {
        int[] womanPartner = new int[N];
        boolean[] manFree = new boolean[N];
        Arrays.fill(womanPartner, -1);
        Arrays.fill(manFree, false);

        int freeCount = N;

        while (freeCount > 0) {
            int m;
            for (m = 0; m < N; m++)
                if (!manFree[m])
                    break;

            for (int i = 0; i < N && !manFree[m]; i++) {
                int w = menPref[m][i];

                if (womanPartner[w] == -1) {
                    womanPartner[w] = m;
                    manFree[m] = true;
                    freeCount--;
                } else {
                    int m1 = womanPartner[w];
                    if (prefersNewProposal(womenPref, w, m, m1)) {
                        womanPartner[w] = m;
                        manFree[m] = true;
                        manFree[m1] = false;
                    }
                }
            }
        }

        // Output the stable pairs
        System.out.println("\nFinal Stable Matches:");
        System.out.println("Woman   Man");
        for (int i = 0; i < N; i++)
            System.out.println(" " + i + "       " + womanPartner[i]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of men/women (N): ");
        N = sc.nextInt();

        int[][] menPref = new int[N][N];
        int[][] womenPref = new int[N][N];

        System.out.println("\nEnter Men's preference lists:");
        for (int i = 0; i < N; i++) {
            System.out.println("Preferences for Man " + i);
            for (int j = 0; j < N; j++) {
                menPref[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nEnter Women's preference lists:");
        for (int i = 0; i < N; i++) {
            System.out.println("Preferences for Woman " + i);
            for (int j = 0; j < N; j++) {
                womenPref[i][j] = sc.nextInt();
            }
        }

        stableMarriage(menPref, womenPref);
}
}