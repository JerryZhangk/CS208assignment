package assignment6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class assignment6_Q1 {
    public static PriorityQueue<good> restoreC;
    public static PriorityQueue<good> restoreP;
    public static PriorityQueue<Integer> restoreDiff;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long budget = in.nextLong();
        int coupon = in.nextInt();

        int res = 0;
        good[] goods = new good[n];
        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            int c = in.nextInt();
            goods[i] = new good(p, c);
        }
        restoreC = new PriorityQueue<>(new Comparator<good>() {
            @Override
            public int compare(good o1, good o2) {
                return o1.c - o2.c;
            }
        });

        restoreP = new PriorityQueue<>(new Comparator<good>() {
            @Override
            public int compare(good o1, good o2) {
                return o1.p - o2.p;
            }
        });
        restoreDiff = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        restoreDiff.add(1000000001);
        for (int i = 0; i < goods.length; i++) {
            restoreC.add(goods[i]);
            restoreP.add(goods[i]);
        }
        for (int i = 0; i < coupon; i++) {
            restoreDiff.add(0);
        }
        while (!restoreP.isEmpty() && !restoreC.isEmpty()) {

            long a = 0;
            if (restoreP.peek().target) {
                restoreP.poll();
                continue;
            }
            a = restoreP.peek().p;

            long b = 0;
            if (restoreC.peek().target) {
                restoreC.poll();
                continue;
            }
            b = restoreC.peek().c + restoreDiff.peek();
            long c = Math.min(a, b);
            if (budget < c) {
                break;
            } else {
                if (b < a) {
                    good k = restoreC.poll();
                    int kk = restoreDiff.poll();
                    budget -= b;
                    restoreDiff.add(k.p - k.c);
                    k.target = true;
                    res++;
                } else {
                    good k = restoreP.poll();
                    budget -= a;
//                    restoreDiff.add(k.p-k.c);
                    res++;
                    k.target = true;
                }
            }
        }
        out.println(res);
        out.close();

    }
}

class good {
    int p;
    int c;
    int dif;
    boolean target;

    public good(int p, int c) {
        this.p = p;
        this.c = c;
        this.dif = p - c;
    }
}
