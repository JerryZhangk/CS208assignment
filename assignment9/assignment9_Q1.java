package assignment9;

import java.io.*;
import java.util.StringTokenizer;

public class assignment9_Q1 {
    public static int[] val;
    public static node[] nodes;
    public static node[] leaf;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        val = new int[n + 1];
        leaf = new node[n + 1];
        for (int i = 1; i <= n; i++) {
            val[i] = in.nextInt();
        }
        nodes = new node[ 4* n];// the first one is null.
        merge(1, n, 1);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            if (a == 1) {
                out.println(query(b, c, 1));
            } else {
                change(b, c, 1);
            }
        }
        out.close();

    }

    public static long merge(int l, int r, int index) {// from 1.
        if (l == r) {
            nodes[index] = new node(l, r, val[l]);
            leaf[l] = nodes[index];
            leaf[l].index = index;
            return val[l];
        }
        long left = 0;
        long right = 0;
        int mid = (l + r) / 2;
        left = merge(l, mid, 2 * index);
        right = merge(mid + 1, r, 2 * index + 1);
        nodes[index] = new node(l, r, left + right);
        return left + right;
    }

    public static boolean check(int l, int r, int index) {
        return (nodes[index].l <= l && l <= nodes[index].r) || (nodes[index].l <= r && r <= nodes[index].r)
                || (l <= nodes[index].l && nodes[index].l <= r) || (l <= nodes[index].r && nodes[index].r <= r);
//        if (nodes[index].l > r) return false;
//        if (nodes[index].r < l) return false;
//        return true;
    }

    public static long query(int l, int r, int index) {// form 1 .
        long left = 0;
        long right = 0;
        if (nodes[index].l >= l && nodes[index].r <= r) {
            return nodes[index].sum;
        } else {
            if (nodes[index].l != nodes[index].r) {
                if (check(l, r, index * 2)) {
                    left = query(l, r, index * 2);
                }
                if (check(l, r, index * 2 + 1)) {
                    right = query(l, r, index * 2 + 1);
                }
            }
        }
        return left + right;
    }

    public static void change(int target, int val, int index) {
        node tem = leaf[target];
        int index2 = tem.index;
        long dif = val - tem.sum;
        while (index2 > 0) {
            nodes[index2].sum += dif;
            index2 /= 2;
        }
    }
}

class node {
    int l;
    int r;
    long sum;
    int index;

    public node(int l, int r, long sum) {
        this.sum = sum;
        this.r = r;
        this.l = l;
    }

}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}