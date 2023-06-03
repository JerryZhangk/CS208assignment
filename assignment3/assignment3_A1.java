package assignment3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class assignment3_A1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        long result = 0;
        int n = in.nextInt();
        int m = in.nextInt();
        long[] apple = new long[n];
        long[] basket = new long[m];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            basket[i] = in.nextLong();
            sum += basket[i];
        }
        for (int i = 0; i < n; i++) {
            apple[i] = in.nextLong();
        }
        Arrays.sort(basket);
//        Arrays.sort(apple);
        if (sum <= n) {
            for (int i = 1; i < m; i++) {
                basket[i] += basket[i - 1];
            }
            for (int i = 0; i < m; i++) {
                long ak = basket[i];// 前 i 个篮子装几个果
                if (ak < n) {
                    result += 2 * apple[(int) ak - 1];
                } else if (ak == n) {
                    result += 2 * apple[(int) ak - 1];
                    break;
                } else {
                    result += 2 * apple[n - 1];
                    break;
                }
            }
        } else {
            long kk = n - 1;
            for (int i = m - 1; i >= 0; i--) {
                long oo = kk -basket[i];
                result+=2*apple[(int)kk];
                if (oo>=0){
                    kk = oo ;
                }else {
                    break;
                }
            }
        }
        out.println(result);
        out.close();
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