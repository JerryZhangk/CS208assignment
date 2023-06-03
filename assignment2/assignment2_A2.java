//package assignment2;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class assignment2_A2 {
//    public static void main(String[] args) {
//        QReader in = new QReader();
//        QWriter out = new QWriter();
//        int testCase = in.nextInt();
//
//        for (int ddd = 0; ddd < testCase; ddd++) {
//            boolean ifHave = false;
//            int nodeNum = in.nextInt();
//            int edgeNum = in.nextInt();
//            node[] node = new node[2 * nodeNum];// 偶数为in 奇数为out
//            for (int i = 0; i < 2 * nodeNum; i++) {
//                node[i] = new node();
//                node[i].index = i;
//                node[i].isVisited= new boolean[2*nodeNum];
//                node[i].depth = new int[2*nodeNum];
//
//            }
//            for (int i = 0; i < edgeNum; i++) {
//                int a = in.nextInt() - 1;
//                int b = in.nextInt() - 1;
//                node[2 * a + 1].children.add(node[2 * b]);
//                node[2 * b].children.add(node[2 * a + 1]);
//            }
//            node[] queue = new node[2 * nodeNum];
//            int result = 2 * nodeNum + 1;
//            for (int i = 0; i < 2 * nodeNum; i++) {//对每一个节点都进行bfs
//                int front = 0;
//                int rear = 0;
//                rear++;
//                node[i].isVisited[i]= true;
//                queue[0] = node[i];
//                while (front != rear) {
//                    for (int j = 0; j < queue[front].children.size(); j++) {
//                        if (!queue[front].children.get(j).isVisited[i]) {
//                            queue[front].children.get(j).depth[i]= queue[front].depth[i] + 1;
//                            queue[front].children.get(j).isVisited[i] = true;
//                            queue[rear] = queue[front].children.get(j);
//                            rear++;
//                        } else {
//                            if (queue[front].children.get(j).depth[i] == queue[front].depth[i] + 1) {
//                                if(2 * queue[front].children.get(j).depth[i] < result){
//                                    result = 2 * queue[front].children.get(j).depth[i];
//                                    ifHave = true;
//                                }
//                            }
//                        }
//                    }
//                    front++;
//                }
//                if (result==4)break;
//            }
//            if (ifHave) {
//                out.println(result);
//            } else {
//                out.println(-1);
//            }
//        }
//            out.close();
//        }
//    }
//
//
//
//    class node {
//    int index ;
//        int []depth;
//        ArrayList<node> children = new ArrayList<>();
//        boolean[] isVisited;
//    }