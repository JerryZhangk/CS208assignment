package assignment6;

import java.io.*;
import java.util.*;

public class assignment6_q2 {
    public static tree[] trees;
    public static int res = 1;
    public static int k;
    public static int idle;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        k = in.nextInt();
        trees = new tree[n + 1];
        for (int i = 1; i < n + 1; i++) {
            trees[i] = new tree(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            trees[a].children.add(trees[b]);
            trees[b].children.add(trees[a]);
        }

        for (int i = 1; i < trees.length; i++) {
            idle = i;
            if (trees[i].children.size() > 1) {
                break;
            }
        }
        trees[idle].isVisited = true;
        trees[idle].depth = 0;
        dfs(trees[idle]);
        for (int i = 1; i < trees.length; i++) {
            trees[i].isVisited = false;
        }

        out.println(res);
        out.close();

    }
    public static void dfs(tree t) {
        int cnt = 0;
        if (t.children.size() == 1) {
            t.isLeaf = true;
            t.depth = 1;
            return ;
        }
        for (int i = 0; i < t.children.size(); i++) {
            if (!t.children.get(i).isVisited) {
                t.children.get(i).isVisited = true;
                dfs(t.children.get(i));
                t.children.get(i).prev = t;
                t.depth = Math.max(t.depth, t.children.get(i).depth + 1);
                t.queue.add(t.children.get(i).depth);
                cnt++;
            }
        }
//        t.update(depth);
        int a = t.queue.poll();
        int b = 0;
        while (!t.queue.isEmpty()) {
            b = t.queue.peek();
            if (a + b > k) {
                t.queue.poll();
                res++;
                a = b;
            }else break;
        }
        t.depth = a + 1;
    }
}

class tree {
    int index;
    int depth = 1;
    boolean isLeaf;
    boolean isVisited;
    tree prev;
    ArrayList<tree> children = new ArrayList<>();
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public tree(int index) {
        this.index = index;
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