package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15651 {

    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        func(0);

        System.out.println(sb);
    }

    static void func(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) sb.append(arr[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= n; i++) {
            arr[k] = i;
            func(k+1);
        }
    }
}

