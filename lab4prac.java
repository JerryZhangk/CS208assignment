//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class lab4prac {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int n = in.nextInt();
//        assignment9.node[] assignment9.node = new assignment9.node[n];
//        for (int i = 0; i < n; i++) {
//            assignment9.node[i] = new assignment9.node(i);
//            assignment9.node[i].depth=i;
//        }
//        for (int i = 0; i < n - 1; i++) {
//            assignment9.node[i].child.add(assignment9.node[i + 1]);
//            assignment9.node[i + 1].child.add(assignment9.node[i]);
//        }
//        int a ;
//        for (int i = 0; i <n; i++) {
//            a = in.nextInt();
//        }// waste the input
//        int[] Ai = new int[n];
//        for (int i = 0; i <n; i++) {
//            Ai[i]= in.nextInt()-1;
//        }
//        for (int i = 0; i <n; i++) {
//            assignment9.node[i].child.add(assignment9.node[Ai[i]]);
////            assignment9.node[Ai[i]].child.add(assignment9.node[i]);
//        }
//
//        assignment9.node[] queue = new assignment9.node[n];
//        int front =0;
//        int rear = 0;
//        rear++;
//        queue[0] = assignment9.node[0];
//        assignment9.node[0].isVisited = true;
//        while (front!=rear){
//            for (int i = 0; i <queue[front].child.size(); i++) {
//                if (!queue[front].child.get(i).isVisited){
//                    queue[front].child.get(i).depth=Math.min(queue[front].child.get(i).depth,queue[front].depth+1);
//                    queue[rear] = queue[front].child.get(i);
//                    queue[front].child.get(i).isVisited = true;
//                    rear++;
//                }
//            }
//            front++;
//        }
//        for (int i = 0; i <n; i++) {
//            System.out.print(assignment9.node[i].depth+" ");
//        }
//
//
//    }
//}
//
//class assignment9.node {
//    public assignment9.node(int index) {
//        this.index = index;
//    }
//
//    int index;
//    ArrayList<assignment9.node> child = new ArrayList<>();
//    int depth;
//    boolean isVisited;
//}
//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}