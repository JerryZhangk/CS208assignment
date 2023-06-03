package assignment7;

import java.io.*;
import java.util.StringTokenizer;

public class assignment7_Q2 {
    static int n;
    static int[] array;
    static int[] dif;
    static int[][] matrix;

    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        n = in.nextInt();
        array = new int[n];
        dif = new int[n - 1];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            dif[i] = Math.abs(array[i + 1] - array[i]);
        }

        if (n == 1) {
            out.println(1);
        } else {
            query(1, n - 1);//打表
            int res = 0;
            for (int i = 1; i <= n - 1; i++) {
                res = Math.max(binary(i, n - 1) - i + 1, res);
            }
            out.println(res);
        }
        out.close();
    }

    public static int binary(int l, int r) {
        int L = l;
        int mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (GcdQuery(L, mid) == 1) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static int GcdQuery(int l, int r) {// l from 1
        int s = (int) (Math.log(r - l + 1) / Math.log(2));
        int kk = (int) (r - Math.pow(2, s) + 1);
        return findGcd(matrix[s][l - 1], matrix[s][kk - 1]);
    }

    public static int findGcd(int a, int b) {//两个数进行找gcd
        if (a > b) {//找小的那个
            int tem = a;
            a = b;
            b = tem;
        }
        if (a == 0) {
            return b;
        }
        if (b % a == 0) {
            return a;
        }
        return findGcd(a, b % a);
    }

    public static void query(int l, int r) {//打一个表 difference 是全部的， 然后返回一个表格
        int s = (int) (Math.log(r - l + 1) / Math.log(2));
        matrix = new int[s + 1][r - l + 1];
        int cnt = 0;
        for (int i = l - 1; i < r; i++) {// l begins from 1.
            matrix[0][cnt] = dif[i];
            cnt++;
        }
        for (int i = 1; i <= s; i++) {
            int range = (int) Math.pow(2, i);
            for (int j = 0; j < matrix[i - 1].length - range / 2; j++) {
                matrix[i][j] = findGcd(matrix[i - 1][j], matrix[i - 1][j + range / 2]);
            }
        }
    }

}
