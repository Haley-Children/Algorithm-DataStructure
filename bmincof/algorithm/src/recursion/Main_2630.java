package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630 {

    static int[][] arr;
    static int[] counts = {0, 0};   // {white, black}
    static final int WHITE = 0;
    static final int BLUE = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0,0, n);

        System.out.println(counts[WHITE]);
        System.out.println(counts[BLUE]);

    }

    static void func(int rs, int cs, int n) {
        if(n == 1) {
            counts[arr[rs][cs]]++;
            return;
        }
        int white = 0;
        int blue = 0;

        for (int i = rs; i < rs + n; i++) {
            for (int j = cs; j < cs + n; j++) {
                if (arr[i][j] == WHITE) white++;
                else blue++;
            }
        }

        if (white == n*n) counts[WHITE]++;
        else if (blue == n*n) counts[BLUE]++;
        else {
            int mid = n / 2;
            func(rs, cs, mid);
            func(rs, cs + mid, mid);
            func(rs + mid, cs, mid);
            func(rs + mid, cs + mid, mid);
        }

    }
}

