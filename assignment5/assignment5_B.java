package assignment5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class assignment5_B {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int test = in.nextInt();
        for (int jjjj = 0; jjjj < test; jjjj++) {
            int n = in.nextInt();
            people[] people = new people[n];
            for (int i = 0; i < n; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                people[i] = new people(a, b);
            }
            Arrays.sort(people, new Comparator<people>() {
                @Override
                public int compare(people o1, people o2) {
                    return o2.easy - o1.easy;
                }
            });
            PriorityQueue<people> queue = new PriorityQueue<>(new Comparator<people>() {
                @Override
                public int compare(people o1, people o2) {
                    return o1.cost - o2.cost;
                }
            });
            int size = 0;
            long cost = 0;
            int l = 0;
            while (l < n) {
                int r = findRight(l, people, n);
                for (int i = l; i <= r; i++) {
                    queue.add(people[i]);
                }
                size = n - queue.size();
                if (people[l].easy > size) {
                    int k = people[l].easy - size;
                    for (int i = 0; i < k; i++) {
                        people p = queue.poll();
                        cost += p.cost;
                    }
                }
                l = r + 1;
            }

            out.println(cost);
        }
        out.close();
    }

    public static int findRight(int l, people[] people, int n) {// 获得的和返回的都是指针
        int easy = people[l].easy;
        int r = l;
        while (r < n && people[r].easy == easy) {
            r++;
        }
        return r - 1;
    }
}

class people {
    int easy;
    int cost;

    public people(int easy, int cost) {
        this.easy = easy;
        this.cost = cost;
    }
}
