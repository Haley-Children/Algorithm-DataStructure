package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15656 {

    static int n, m;
    static int[] inputs;
    static int[] arr;
    static StringBuilder sb = new StringBuilder(1000);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        inputs = new int[n];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputs);
        bt(0);

        System.out.println(sb);
    }

    static void bt(int k) {
        if (k == m) {
            for (int i = 0; i < k; i++) sb.append(arr[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[k] = inputs[i];
            bt(k+1);
        }
    }

}

