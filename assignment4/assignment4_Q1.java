package assignment4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class assignment4_Q1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String str2 = in.next();
        int keyWord = in.nextInt();
        ArrayList<inter> inters = new ArrayList<>();
        for (int i = 0; i < keyWord; i++) {
            String str = in.next();
            int X = 0;
            int length = str.length();
            int[][] array = new int[length][94];
            array[0][str.charAt(0) - 33] = 1;
            for (int j = 1; j < length; j++) {
                for (int k = 0; k < 94; k++) {
                    if (str.charAt(j) == k + 33) {
                        array[j][k] = j + 1;
                    } else {
                        array[j][k] = array[X][k];
                    }
                }
                X = array[X][str.charAt(j) - 33];
            }
            int k = 0;
            for (int j = 0; j < str2.length(); j++) {
                if (k < str.length()) {
                    k = array[k][str2.charAt(j) - 33];
                }
                if (k == str.length()) {
                    inters.add(new inter(j - str.length() + 1, j, i));
                    k--;
                }
            }
        }
        inters.sort(new Comparator<inter>() {
            @Override
            public int compare(inter o1, inter o2) {
                return o1.end - o2.end;
            }
        });
        long count = 0;
        if (inters.size() == 0) {
            out.println(0);
        } else {
            count = 1;
            int t = inters.get(0).end;
            int n = inters.size();
            for (int i = 0; i < n; i++) {
                if (i + 1 < n && inters.get(i + 1).begin > t) {
                    t = inters.get(i + 1).end;
                    count++;
                }
            }
            if (inters.get(n - 1).begin > t) {
                count++;
            }
            out.println(count);
        }
        out.close();
    }
}

class inter {
    int begin;
    int end;
    int index;

    public inter(int begin, int end, int index) {
        this.begin = begin;
        this.end = end;
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