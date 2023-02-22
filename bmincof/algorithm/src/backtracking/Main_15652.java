package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15652 {

    static StringBuilder sb = new StringBuilder(1000);
    static int n, m;
    static int[] arr = new int[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        func(0);
        System.out.println(sb);
    }

    static void func(int k) {
        if (k == m) {
            for (int i = 0; i < k; i++) sb.append(arr[i]).append(' ');
            sb.append('\n');
            return;
        }

        int start = k == 0 ? 1 : arr[k-1];
        for (int i = start; i <= n; i++) {
            arr[k] = i;
            func(k+1);
        }
    }

}

