package assignment8;

public class assignment8_Q2 {
    public static int[] array;
    public static QWriter out;
    public static void main(String[] args) {
        QReader in = new QReader();
        out = new QWriter();
        int n = in.nextInt();
        array = new int[n];
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            array[i] = a;
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        act(0, array.length - 1, max, min);
        out.println(-1 + " " + -1);
        out.close();
    }
    public static void act(int l, int r, int max, int min) {
        if (l >= r) {
            return;
        }
        if (max == min) {
            return;
        }
        int wall = (max + min) / 2;
        int bd = recursion(l, r, wall);
        act(l, l+bd-1, wall, min);
        act(l+bd, r, max, wall + 1);
    }
    public static int recursion(int l, int r, int wall) {//接受的是下标 返回的是bd
        if (l == r) {
            if (array[l] <= wall) {
                return 1;
            } else {
                return 0;
            }
        }//end of recursion
        int mid = (l + r) / 2;
        int lbd = recursion(l, mid, wall);
        int rbd = recursion(mid + 1, r, wall);
        reverse(l + lbd, mid + rbd);
        return lbd + rbd;
    }

    public static void reverse(int l, int r) {
        if (l < r) {
            int a = l;
            int b = r;
            while (a < b) {
                int tem = array[b];
                array[b] = array[a];
                array[a] = tem;
                a++;
                b--;
            }
            out.println((l + 1) + " " + (r + 1));
        }
    }
}
