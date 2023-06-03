package assignment10;

import java.io.*;
import java.util.StringTokenizer;

public class assignment10_Q2 {
    static int[][] dp;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int test = in.nextInt();

        for (int jjjj = 0; jjjj < test; jjjj++) {
            String str = in.next();
            int length = str.length();
            dp = new int[length][length];
            win1(str);
            int size = 4;
            while (size <= length) {
                build(str, size);
                size += 2;
            }
            int res = dp[0][str.length() - 1];
            if (res == 1) {
                out.println("Alice");
            } else if (res == 2) {
                out.println("Draw");
            } else if (res == 3) {
                out.println("Bob");
            } else {
                System.out.println("wrong");
            }

        }
        out.close();
    }

    public static int win1(String str) {// Alice : 1 draw : 2  Bob : 3
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = 2;//平局是2
            } else {
                dp[i][i + 1] = 1;
            }
        }
        return -1;
    }

    public static int check(String str, int index1, int index2, int sonL, int sonR) {
        if (str.charAt(index1) == str.charAt(index2)) {
            return dp[sonL][sonR];
        } else if (str.charAt(index1) < str.charAt(index2)) {
            return 1;
        } else {
            return 3;
        }

    }

    public static void build(String str, int size) {
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        for (int i = 0; i < str.length() - size + 1; i++) {
            num1 = check(str, i, i + size - 1, i + 1, i + size - 2);
            num2 = check(str, i, i + 1, i + 2, i + size - 1);
            num3 = check(str, i + size - 1, i, i + 1, i + size - 2);
            num4 = check(str, i + size - 1, i + size - 2, i, i + size - 3);
            num1 = Math.max(num1, num2);
            num3 = Math.max(num3, num4);
            if (num1 ==  1 || num3 == 1) {
                dp[i][i + size - 1] = 1;
            } else if (num1 == 2 || num3 == 2) {
                dp[i][i + size - 1] = 2;
            } else if (num1 == 3 || num3 == 3) {
                dp[i][i + size - 1] = 3;
            }

        }
    }
}

