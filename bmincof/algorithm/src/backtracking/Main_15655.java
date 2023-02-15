package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15655 {

    static StringBuilder sb = new StringBuilder(1000);
    static int n, m;
    static int[] buffer = new int[10];
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()) + 1;
        m = Integer.parseInt(st.nextToken()) + 1;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        func(1);
        System.out.println(sb);
    }

    static void func(int k) {
        if(k == m) {
            for (int i = 1; i < k; i++) sb.append(buffer[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = k; i < n; i++) {
            if(arr[i] > buffer[k - 1]) {
                buffer[k] = arr[i];
                func(k+1);
            }
        }
    }
}

