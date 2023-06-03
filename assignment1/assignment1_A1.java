package assignment1;

import java.io.*;
import java.util.*;

public class assignment1_A1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int N = in.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        String[] answer = new String[2*N];
        for (int i = 0; i < 2 * N; i++) {
            String s = in.next();
            map.put(s, i);//map 中女生的排序是从N到2*N-1.
            answer[i] = s;
        }
        man[] man = new man[N];
        woman[] woman = new woman[N];

        for (int i = 0; i < N; i++) {
            man[i] = new man();
            man[i].index = i;
            man[i].wife = -1;
            woman[i] = new woman();
            woman[i].index = i + N;
        }

        for (int i = 0; i < N; i++) {
            man[i].woman = new int[N];
            woman[i].manInverse = new int[N];
        }//init.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                man[i].woman[j] = map.get(in.next());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int a = map.get(in.next());
                woman[i].manInverse[a] = j;
            }
        }

        Stack<man> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            stack.push(man[i]);
        }

        while (!stack.isEmpty()) {
            man tem = stack.pop();
            for (int i = tem.failNum; i < N; i++) {
                if (!woman[tem.woman[i] - N].married) {
                    woman[tem.woman[i] - N].married = true;
                    woman[tem.woman[i] - N].fiance = tem.index;
                    tem.wife = tem.woman[i];
                    tem.failNum = i;//上次在自己喜欢的第 i 上失败了。
                    break;
                } else {
                    int num = woman[tem.woman[i] - N].fiance;
                    if (woman[tem.woman[i] - N].manInverse[num] < woman[tem.woman[i] - N].manInverse[tem.index]) {//更喜欢丈夫
//                        tem.failNum = i;
                    } else {
                        man[num].wife = -1;
                        stack.push(man[num]);
                        woman[tem.woman[i] - N].fiance = tem.index;
                        tem.wife = tem.woman[i];
                        tem.failNum = i;
                        break;
                    }
                }
            }
        }

//        for (int i = 0; i <N; i++) {
//                out.println(answer[i]+ " " + answer[assignment1.man[i].wife]);
//        }
        Set<Map.Entry<String,Integer>> entries = map.entrySet();
        for(Map.Entry<String,Integer> entry:entries){
            System.out.println(entry);
        }
        out.close();
    }
}

class man {
    int index;
    int failNum;
    int[] woman;
    int wife;
}

class woman {
    int index;
    boolean married;
    int[] manInverse;
    int fiance;
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