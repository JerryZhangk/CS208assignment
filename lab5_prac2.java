import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class lab5_prac2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = in.next();
            int start = in.nextInt();
            int end = in.nextInt();
            jobs[i] = new Job(name, start, end);
        }
        Arrays.sort(jobs, Comparator.comparingInt(e -> e.end));
        int lastEndAt = Integer.MIN_VALUE;
        ArrayList<Job> availableJobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (jobs[i].start >= lastEndAt) {
                availableJobs.add(jobs[i]);
                lastEndAt = jobs[i].end;
            }
        }
        for (Job job:availableJobs){
            System.out.print(job.name + " ");
        }
    }
}

class Job {
    String name;
    int start;
    int end;

    public Job(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}
