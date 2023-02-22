package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15666 {

    static StringBuilder sb = new StringBuilder(200);
    static int n, m;
    static int[] arr, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        ans = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        bt(0, 0);
        System.out.println(sb);
    }

    static void bt(int k, int idx) {
        if(k == m) {
            for (int i = 0; i < k; i++) sb.append(ans[i]).append(' ');
            sb.append('\n');
            return;
        }

        int last = 0;
        for (int i = idx; i < n; i++) {
            if(last != arr[i]) {
                ans[k] = arr[i];
                last = ans[k];
                bt(k + 1, i);
            }
        }

    }

}

