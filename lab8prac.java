import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class lab8prac {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        for (int jjjj = 0; jjjj < test; jjjj++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextInt();
            }
            Queue<Integer> q1 = new PriorityQueue<>(new Comparator<Integer>() { //
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            PriorityQueue<Integer> q2 = new PriorityQueue<>(new Comparator<Integer>() {//
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            for (int i = 0; i < n; i++) {
                q1.add(array[i]);
                q2.add(array[i]);
            }
            int result1 = 0;
            int result2 = 0;
            int cost1 = 0;
            int cost2 = 0;
            cost1 += q1.poll();
            cost1 += q1.poll();
            result1 += cost1;
            while (!q1.isEmpty()) {
                cost1 += q1.poll();
                result1 += cost1;
            }

            System.out.println(result1);

            if (n <= k) {
                for (int i = 0; i < n; i++) {
                    cost2 += q2.poll();
                }
                result2 += cost2;

            } else {
                int uu = (n) % (k - 1) ;
                while (uu<=k){
                    uu+=k-1;
                }
                uu-=k-1;
                for (int i = 0; i < uu; i++) {
                    cost2 += q2.poll();
                }
                if (uu != 0)
                    result2 += cost2;
                int qq = (n - uu) / (k - 1);
                for (int i = 0; i < qq; i++) {
                    for (int j = 0; j < k - 1; j++) {
                        cost2 += q2.poll();
                    }
                    result2 += cost2;
                }

            }
            System.out.println(result2);
        }
    }
}
