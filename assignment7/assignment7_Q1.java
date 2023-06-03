package assignment7;

import java.util.Scanner;
public class assignment7_Q1 {
    static long value1;


    
    static long value2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long l = in.nextLong();
        long r = in.nextLong();
        String str = Long.toBinaryString(n);
        long size2 = (long) Math.pow(2, str.length()) - 1;
        if (l == 0) {
            System.out.println(find(r, size2, n));
        } else {
            value1 = find(l - 1, size2, n);
            value2 = find(r, size2, n);
            System.out.println(value2 - value1);
        }
    }
    public static long find(long pixel, long r, long val) {//计算从1到n的数值。
        long mid = r / 2 + 1;
        if (pixel == r) {
            return val;
        }
        if (pixel < mid) {
            val = val / 2;
            r = mid - 1;
            return (find(pixel, r, val));
        } else if (pixel > mid) {
            long mod = val % 2;
            val = val / 2;
            r = mid - 1;
            pixel = pixel - mid;
            return mod + val + find(pixel, r, val);
        }
        return val / 2 + val % 2;
    }
}
