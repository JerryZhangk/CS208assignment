package assignment8;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class assignment8_Q1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        point[] points = new point[n];
        for (int i = 0; i < n; i++) {
            long x = in.nextLong();
            long y = in.nextLong();
            points[i] = new point(x, y);
        }//init.
        Arrays.sort(points, new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                return (int) (o1.x - o2.x);
            }
        });
        out.println(Closest_Pair(points));
        out.close();
    }

    public static long Closest_Pair(point[] points) {
        if (points.length <= 3) {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    point cur = points[i];
                    point tmp = points[j];
                    long x = cur.x - tmp.x;
                    long y = cur.y - tmp.y;
                    long dis = x * x + y * y;
                    min = Math.min(dis, min);
                }
            }
            return min;
        }
        int size = points.length / 2;
        long line = points[size].x;
        point[] l = new point[size];
        point[] r = new point[points.length - size];
        System.arraycopy(points, 0, l, 0, size);
        System.arraycopy(points, size, r, 0, points.length - size);
        long leftVal = Closest_Pair(l);
        long rightVal = Closest_Pair(r);
        long min = Math.min(leftVal, rightVal);
        int pixelLeft = size;
        while (pixelLeft > 0 && line - points[pixelLeft].x <= min) {
            pixelLeft--;
        }
        if (line - points[0].x <= min) {
            pixelLeft = 0;
        }
        int pixelRight = size;
        while (pixelRight < points.length - 1 && points[pixelRight].x - line <= min) {
            pixelRight++;
        }
        if (points[points.length - 1].x - line <= min) {
            pixelRight = points.length - 1;
        }

        point[] np = new point[pixelRight - pixelLeft + 1];
        int tmp = 0;
        for (int i = pixelLeft; i <= pixelRight; i++) {
            np[tmp] = points[i];
            tmp++;
        }

        Arrays.sort(np, new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                return (int) (o1.y - o2.y);
            }
        });
        for (int i = 0; i < np.length; i++) {
            point tem = np[i];
            for (int j = i + 1; j < np.length && j - i <= 6; j++) {
                point com = np[j];
                long x = com.x - tem.x;
                long y = com.y - tem.y;
                long dis = x * x + y * y;
                min = Math.min(dis, min);
            }
        }
        return min;

    }


}

class point {
    long x;
    long y;

    public point(long x, long y) {
        this.x = x;
        this.y = y;
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