import java.util.*;

class Job {
    int start, end, weight;

    public Job(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class WIS {

    public static int findLastNonConflicting(Job[] jobs, int i) {
        for (int j = i - 1; j >= 0; j--) {
            if (jobs[j].end <= jobs[i].start)
                return j;
        }
        return -1;
    }

    public static int wIS(Job[] jobs, List<Job> selectedJobs) {
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.end));
        int n = jobs.length;

        int[] dp = new int[n];
        boolean[] included = new boolean[n];
        int[] prev = new int[n];

        dp[0] = jobs[0].weight;
        included[0] = true;
        prev[0] = -1;

        for (int i = 1; i < n; i++) {
            int incl = jobs[i].weight;
            int l = findLastNonConflicting(jobs, i);
            if (l != -1)
                incl += dp[l];

            if (incl > dp[i - 1]) {
                dp[i] = incl;
                included[i] = true;
                prev[i] = l;
            } else {
                dp[i] = dp[i - 1];
                included[i] = false;
            }
        }

        int i = n - 1;
        while (i >= 0) {
            if (included[i]) {
                selectedJobs.add(jobs[i]);
                i = prev[i];
            } else {
                i--;
            }
        }

        Collections.reverse(selectedJobs);
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter start time, end time, and weight for job " + (i + 1) + ":");
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            jobs[i] = new Job(start, end, weight);
        }

        List<Job> selectedJobs = new ArrayList<>();
        int maxProfit = wIS(jobs, selectedJobs);

        System.out.println("Maximum weight of non-overlapping intervals: " + maxProfit);
        System.out.println("Selected intervals (start, end, weight):");
        int sum = 0;
        for (Job job : selectedJobs) {
            System.out.println("(" + job.start + ", " + job.end + ", " + job.weight + ")");
            sum += job.weight;
        }
        System.out.println("Verified sum of selected weights: " + sum);
    }
}
