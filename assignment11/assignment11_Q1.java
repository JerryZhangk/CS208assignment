package assignment11;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class assignment11_Q1 {
    public static int number;
    public static int mod = (int) 1e9 + 7;
    public static int currentIndex = 0;
    static long[][] fp;
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        number = in.nextInt();
        int query = in.nextInt();
        tree[] trees = new tree[number + 1];
        for (int i = 0; i <= number; i++) {
            trees[i] = new tree(i);
        }
        for (int i = 0; i < number - 1; i++) {
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            trees[index1].children.add(trees[index2]);
            trees[index2].children.add(trees[index1]);
        }
        int sumW = 0;
        for (int i = 1; i <= number; i++) {
            trees[i].w = in.nextInt();
            sumW += trees[i].w;
            trees[i].a = in.nextInt();
            trees[i].b = in.nextInt();
        }//建树
        long res = 0;
        long inverse = inv(sumW);
        for (int llll = 0; llll < number; llll++) {
            fp = new long[number + 1][number + 1];
            for (int i = 1; i <= number; i++) {
                trees[i].inverseP = inv(trees[i].b);
                fp[trees[i].index][1] = ((trees[i].a % mod) * (trees[i].inverseP % mod)) % mod;
                fp[trees[i].index][0] = (((trees[i].b - trees[i].a + mod) % mod) * (trees[i].inverseP % mod)) % mod;
            }//建树
            for (int j = 1; j <= number; j++) {
                trees[j].isVisited = false;
                trees[j].mergeNum = 1;
            }
            currentIndex++;
            trees[currentIndex].isVisited = true;
            fp[currentIndex][1] = ((trees[currentIndex].w % mod) * (inverse % mod)) % mod;
            fp[currentIndex][0] = (((sumW - trees[currentIndex].w + mod) % mod) * (inverse % mod)) % mod;
            dfs(trees[currentIndex]);
            res = ((res + fp[currentIndex][query]) % mod + mod) % mod;
        }
        out.println(res);
        out.close();
    }
    public static void dfs(tree tree) {
        for (int i = 0; i < tree.children.size(); i++) {
            if (!tree.children.get(i).isVisited) {
                tree.children.get(i).isVisited = true;
                dfs(tree.children.get(i));
                tree.mergeNum += tree.children.get(i).mergeNum;
                for (int j = tree.mergeNum; j > 0; j--) {
                    long tmp = 0;
                    int a = Math.min(tree.mergeNum - tree.children.get(i).mergeNum, j);
                    for (int k = 0; k <= tree.mergeNum - j; k++) {
                        if (k > tree.children.get(i).mergeNum) {break;}
                        if (k >= a) {break;}
                        long tem = ((fp[tree.index][a - k] % mod) * (fp[tree.children.get(i).index][j - a + k] % mod)) % mod;
                        tmp = ((tem % mod) + (tmp % mod)) % mod;
                    }
                    fp[tree.index][j] = tmp;
                }
            }
        }
    }

    public static long inv(long a) { // 求逆元的方法
        long b = mod - 2;
        long ans = 1;
        while (b > 0) {
            if ((b % 2) == 1) {ans = (ans * a) % mod;}
            a = (a * a) % mod;
            b /= 2;
        }
        return ans % mod;
    }
}
class tree {
    int index;
    int w;
    int a;
    int b;
    long inverseP;
    int mergeNum = 1;
    ArrayList<tree> children = new ArrayList<>();
    boolean isVisited;
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
