package assignment5;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class assignment5_A {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long coins = in.nextLong();
        long result = 0;
        int[] price = new int[n];
        int[] weight = new int[n];
        expense[] ex = new expense[n];
        for (int i = 0; i < n; i++) {
            price[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            weight[i] = in.nextInt();
            int cash = find(price[i]);
            ex[i] = new expense(cash*weight[i],i);// 已经计算出每一个的expense
        }
        PriorityQueue<expense> queue = new PriorityQueue<>(new Comparator<expense>() {
            @Override
            public int compare(expense o1, expense o2) {
                return (int)(o1.val-o2.val);
            }
        });
        for (int i = 0; i <n; i++) {
            if (price[i]%100 == 0) continue;
            int a = 100 - find(price[i]);
            if (coins>=a){
                coins-=a;
                queue.add(ex[i]);
            }else {
                if (!queue.isEmpty()){
                    expense k = queue.peek();
                    if (ex[i].val<=k.val){// 不如直接搞
                        result+=ex[i].val;
                        coins+=100;
                        coins-=a;
                    }else {
                        expense kk = queue.poll();
                        coins+=100;
                        queue.add(ex[i]);
                        result+=kk.val;
                        coins-=a;
                    }
                }else {
                    result+=ex[i].val;
                    coins+=100;
                    coins-=a;
                }
            }
        }
        out.println(result);
        out.close();
    }
    public static int find(int price){
        if(price<=100){
            return 100 - price;
        }
        return find(price-100);
    }
}
class expense{
    long val ;
    int index ;
    public expense(long val,int index ){
        this.index = index;
        this.val= val;
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