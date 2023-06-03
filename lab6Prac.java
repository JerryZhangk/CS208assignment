import java.util.*;

public class lab6Prac {
    public static void main(String[] args) {
        int count = 1;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        interval[] intervals = new interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new interval();
        }
        for (int i = 0; i < n; i++) {
            intervals[i].begin = in.nextInt();
            intervals[i].end = in.nextInt();
        }
        List<interval> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(intervals[i]);
        }
        list.sort(new Comparator<interval>() {
            @Override
            public int compare(interval o1, interval o2) {
                return o1.end - o2.end;
            }
        });
        int t = list.get(0).end;
        for (int i = 0; i <n; i++) {
            if (i+1<n&&list.get(i+1).begin<t){

            }else if (i+1<n) {
                t = list.get(i+1).end;
                count++;
            }
        }
        System.out.println(count);
    }
}

class interval {
    int begin;
    int end;
}