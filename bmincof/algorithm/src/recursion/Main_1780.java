package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1780 {

    static int[][] arr;
    static int[] counts = {0, 0, 0};
    static final int MINUS = 0;
    static final int ZERO = 1;
    static final int PLUS = 2;

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

        System.out.println(counts[MINUS]);
        System.out.println(counts[ZERO]);
        System.out.println(counts[PLUS]);

    }

    static void func(int rs, int cs, int n) {
        if (n == 1) {
            counts[arr[rs][cs] + 1]++;
            return;
        }
        int minus = 0;
        int zero = 0;
        int plus = 0;

        for (int i = rs; i < rs + n; i++) {
            for (int j = cs; j < cs + n; j++) {
                if (arr[i][j] == -1) minus++;
                else if (arr[i][j] == 0) zero++;
                else plus++;
            }
        }

        if (minus == n * n) counts[MINUS]++;
        else if (zero == n * n) counts[ZERO]++;
        else if (plus == n * n) counts[PLUS]++;
        else {
            int offset = n / 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    func(rs + i * offset, cs + j * offset, offset);
                }
            }
        }
    }
}

