import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class assignment12_Q1 {
    static int m;
    static int n;
    static villain source;
    static villain target;
    static villain[] villains;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        m = in.nextInt();
        n = in.nextInt();
        source = new villain(0);
        target = new villain(m + n + 1);
        villains = new villain[m + n + 1];
        for (int i = 1; i < villains.length; i++) {
            villains[i] = new villain(i);
        }
        for (int i = 0; i < m; i++) {
            source.child.add(villains[i + 1]);
            source.weight.add(in.nextInt());
            villains[i + 1].child.add(source);
            villains[i + 1].weight.add(0);
            int num = in.nextInt();
            for (int j = 0; j < num; j++) {
                int a = in.nextInt();
                villains[i + 1].child.add(villains[a + m]);
                villains[i + 1].weight.add(Integer.MAX_VALUE);
                villains[a + m].child.add(villains[i + 1]);
                villains[a + m].weight.add(0);
            }
        }
        for (int i = 1; i < n + 1; i++) {
            villains[i + m].child.add(target);
            villains[i + m].weight.add(in.nextInt());
            target.child.add(villains[i + m]);
            target.weight.add(0);
        }
        while (bfs(source)) {
            augment();
        }
        villain[] queue = new villain[m + n + 2];
        int front = 0;
        int rear = 0;
        rear++;
        queue[front] = source;
        queue[front].flag = 1;
        while (rear != front) {
            for (int i = 0; i < queue[front].child.size(); i++) {
                if (queue[front].child.get(i).flag == 0 && queue[front].weight.get(i) > 0) {
                    queue[front].child.get(i).flag = 1;
                    queue[rear] = queue[front].child.get(i);
                    rear++;
                }
            }
            front++;
        }
        for (int i = 0; i < m; i++) {
            if (villains[i + 1].flag == 1) {
                out.print((i + 1) + " ");
            }
        }
        out.print("\n");
        for (int i = 0; i < n; i++) {
            if (villains[i + 1 + m].flag == 1) {
                out.print((i + 1) + " ");
            }
        }
        out.print("\n");
        int maxFlow = 0;
        for (int i = 0; i < source.child.size(); i++) {
            maxFlow += source.weight.get(i);
        }
        out.println(maxFlow);
        out.close();
    }

    public static void augment() {
        villain tem = target;
        int bottleNeck = target.bottleNeck;
        while (tem.prev != source) {
            tem = tem.prev;
            bottleNeck = Math.min(bottleNeck, tem.bottleNeck);
        }//b<-bottleNeck(P)
        tem = target;
        while (tem!=source){
            int index = tem.prev.child.indexOf(tem);
            tem.prev.weight.set(index,tem.prev.weight.get(index)-bottleNeck);
            int index2 = tem.child.indexOf(tem.prev);
            tem.weight.set(index2,tem.weight.get(index2)+bottleNeck);
            tem = tem.prev;
        }
    }

    public static boolean bfs(villain source) {
        for (int i = 1; i < m + n + 1; i++) {
            villains[i].isVisit = false;
            villains[i].prev = null;
        }
        target.isVisit = false;
        villain[] queue = new villain[m + n + 2];
        int front = 0;
        int rear = 0;
        rear++;
        queue[0] = source;
        queue[0].isVisit = true;
        while (front != rear) {
            for (int i = 0; i < queue[front].child.size(); i++) {
                if ((!queue[front].child.get(i).isVisit) && queue[front].weight.get(i) > 0) {
                    queue[front].child.get(i).isVisit = true;
                    queue[front].child.get(i).prev = queue[front];
                    queue[front].child.get(i).bottleNeck = queue[front].weight.get(i);
                    queue[rear] = queue[front].child.get(i);
                    rear++;
                }
            }
            front++;
            if (queue[front] == target) {
                return true;
            }
        }
        return false;
    }


}

class villain {
    int index;
    boolean isVisit;
    villain prev;
    int bottleNeck;

    ArrayList<villain> child = new ArrayList<>();
    ArrayList<Integer> weight = new ArrayList<>();
    int flag;

    public villain(int index) {
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
