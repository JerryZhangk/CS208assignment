package assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
public class assignment2_A1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int init = in.nextInt() - 1;
        int q = in.nextInt();
        long[] depth = new long[n];
        node[] node = new node[n];
        for (int i = 0; i < n; i++) {
            node[i] = new node(i);
        }
        for (int i = 0; i < m; i++) {
            int num1 = in.nextInt() - 1;
            int num2 = in.nextInt() - 1;
            node[num1].child.add(node[num2]);
            node[num2].child.add(node[num1]);
        }
        node[] queue = new node[n];
        node[init].isVisited = true;
        int front = 0;
        int rear = 0;
        rear++;
        queue[0] = node[init];
        depth[0] = 1;
        while (front != rear) {
            for (int i = 0; i < queue[front].child.size(); i++) {
                if (!queue[front].child.get(i).isVisited) {
                    queue[front].child.get(i).depth = queue[front].depth + 1;
                    depth[queue[front].child.get(i).depth]++;
                    queue[rear] = queue[front].child.get(i);
                    queue[front].child.get(i).isVisited = true;
                    rear++;
                }
            }
            front++;
        }
        for (int i = 1; i < n; i++) {
            depth[i] += depth[i - 1];
        }
        for (int i = 0; i < q - 1; i++) {
            int cc = in.nextInt();
            if (cc <= n - 1) {
                out.print(depth[cc] + " ");
            } else {
                out.print(n+" ");
            }
        }
        int cc = in.nextInt();
        if (cc <= n - 1) {
            out.print(depth[cc]);
        } else {
            out.print(n);
        }
        out.close();
    }

}

class node {
    public node(int index) {
        this.index = index;
    }

    int index;
    ArrayList<node> child = new ArrayList<>();
    boolean isVisited;
    int depth;
}

