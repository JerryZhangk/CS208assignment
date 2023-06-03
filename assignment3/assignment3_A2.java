//import java.io.*;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//
//public class assignment3_A2 {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int test = in.nextInt();
//        for (int jjjj = 0; jjjj < test; jjjj++) {
//            int nodeNum = in.nextInt();
//            int edgeNum = in.nextInt();
//            int[] table = new int[nodeNum];
//            for (int i = 0; i < nodeNum; i++) {
//                table[i] = i;
//            }
//            node[] nodes = new node[nodeNum];
//            for (int i = 0; i < nodeNum; i++) {
//                nodes[i] = new node(i);
//            }
//            int[] store = new int[2 * edgeNum];
//            for (int i = 0; i < edgeNum; i++) {
//                int a = in.nextInt() - 1;
//                int b = in.nextInt() - 1;
//                store[2 * i] = a;
//                store[2 * i + 1] = b;
//                nodes[b].father.add(nodes[a]);
//                nodes[a].children.add(nodes[b]);
//                nodes[b].inDegree++;
//            }
//            for (int i = 0; i < nodeNum; i++) {
//                if (nodes[i].inDegree > 1) {
//                    int a = nodes[i].father.get(0).index;
//                    for (int j = 1; j < nodes[i].inDegree; j++) {
//                        join(a, nodes[i].father.get(j).index, table);
//                    }
//                }
//            }
//            for (int i = 0; i < nodeNum; i++) {
//                if (table[i] != i) {
//                    int ans = find(i, table);
//                    table[i] = ans;
//                }
//            }
//            for (int i = 0; i < store.length; i++) {
//                int a = store[i];
//                store[i] = table[a];
//            }
//            node[] nodes1 = new node[nodeNum];
//            for (int i = 0; i < nodeNum; i++) {
//                nodes1[i] = new node(i);
//            }
//            boolean ifCircle = false;
//            for (int i = 0; i < edgeNum; i++) {
//                int a = store[2 * i];
//                int b = store[2 * i + 1];
//                nodes1[a].isUsed = true;
//                nodes1[b].isUsed = true;
//                nodes1[a].children.add(nodes1[b]);
//                if (a == b) {
//                    ifCircle = true;
//                }
//                nodes1[b].inDegree++;
//                nodes1[b].father.add(nodes1[a]);
//            }
//            int init = table[0];
//            int rear = 1;
//            int front = 0;
//            if (nodes1[init].inDegree != 0) {
//                out.println("No");
//                continue;
//            }
//            node[] queue = new node[nodeNum];
//            queue[front] = nodes1[init];
//            while (front != rear) {
//                for (int i = 0; i < queue[front].children.size(); i++) {
//                    queue[front].children.get(i).inDegree--;
//                    queue[front].children.get(i).depth = Math.max(queue[front].depth + 1, queue[front].children.get(i).depth);
//                    if (queue[front].children.get(i).inDegree == 0) {
//                        queue[rear] = queue[front].children.get(i);
//                        rear++;
//                    }
//                }
//                front++;
//            }
//            for (int i = 0; i < nodeNum; i++) {
//                if (nodes1[i].inDegree != 0) {
//                    ifCircle = true;
//                }
//            }
//            if (ifCircle) {
//                out.println("No");
//                continue;
//            } else {
//                out.println("Yes");
//            }
//            for (int i = 0; i < nodeNum; i++) {
//                nodes[i].isVisited = false;
//                int a = table[i];
//                nodes1[i].depth = nodes1[a].depth;
//                nodes[i].depth = nodes1[i].depth;
//            }
//            nodes[0].val = 1;
//            for (int i = 0; i < nodeNum; i++) {
//                for (int j = 0; j < nodes[i].children.size(); j++) {
//                    if (!nodes[i].children.get(j).isVisited) {
//                        nodes[i].children.get(j).isVisited = true;
//                        nodes[i].children.get(j).val = nodes[i].children.get(j).depth - nodes[i].depth;
//                    } else {
//                        nodes[i].children.get(j).val = Math.max(nodes[i].children.get(j).depth - nodes[i].depth, nodes[i].children.get(j).val);
//                    }
//                }
//            }
//            for (int i = 0; i < nodeNum - 1; i++) {
//                out.print(nodes[i].val + " ");
//            }
//            out.println(nodes[nodeNum - 1].val);
//        }
//        out.close();
//    }
//
//    public static int find(int x, int[] table) {
//        while (table[x] != x) {
//            x = table[x];
//        }
//        return x;
//    }
//
//    public static int[] join(int x, int y, int[] table) {
//        int fx = find(x, table);
//        int fy = find(y, table);
//        if (fx != fy) {
//            table[fx] = fy;
//        }
//        return table;
//    }
//}
//
//
//class node {
//    public node(int index) {
//        this.index = index;
//    }
//
//    int index;
//    int depth;
//    ArrayList<node> children = new ArrayList<>();
//    boolean isVisited;
//    boolean isUsed;
//    boolean isMerge;
//    node mergePoint;
//    int inDegree;
//    int val;
//    ArrayList<node> father = new ArrayList<>();
//}
//
//
