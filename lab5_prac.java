//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//public class lab5_prac {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int nodeNum = in.nextInt();
//        int edge = in.nextInt();
//        assignment9.node[] nodes = new assignment9.node[nodeNum];
//        for (int i = 0; i < nodeNum; i++) {
//            nodes[i] = new assignment9.node();
//            nodes[i].index = i;
//
//        }
//        for (int i = 0; i < edge; i++) {
//            int a = in.nextInt() - 1;
//            int b = in.nextInt() - 1;
//            nodes[a].chlid.add(nodes[b]);
//            nodes[b].in++;
//        }
//        PriorityQueue<assignment9.node> queue = new PriorityQueue<>(new Comparator<assignment9.node>() {
//            @Override
//            public int compare(assignment9.node o1, assignment9.node o2) {
//                return o1.index- o2.index;
//            }
//        });
//        for (int i = 0; i <nodeNum; i++) {
//            if (nodes[i].in ==0){
//                queue.add(nodes[i]);
//            }
//        }
//        while (!queue.isEmpty()){
//            assignment9.node assignment9.node = queue.poll();
//            System.out.println(assignment9.node.index+1);
//            for (int i = 0; i <assignment9.node.chlid.size(); i++) {
//                assignment9.node.chlid.get(i).in --;
//                if (assignment9.node.chlid.get(i).in == 0){
//                    queue.add(assignment9.node.chlid.get(i));
//                }
//            }
//        }
//    }
//}
//
//class assignment9.node {
//    int in;
//    int index;
//    ArrayList<assignment9.node>chlid  = new ArrayList<>();
//
//}