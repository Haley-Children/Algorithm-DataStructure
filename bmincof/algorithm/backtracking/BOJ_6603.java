package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603 {

    static StringBuilder sb;
    static int n;
    static int[] arr, ans;
    static boolean[] isUsed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            sb = new StringBuilder(100);

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;

            arr = new int[n];
            ans = new int[n];
            isUsed = new boolean[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            bt(0, 0);
            System.out.println(sb);
        }
    }

    static void bt(int k, int idx) {
        if(k == 6) {
            for (int i = 0; i < 6; i++) sb.append(ans[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = idx; i < n; i++) {
            if(!isUsed[i]) {
                ans[k] = arr[i];
                isUsed[i] = true;
                bt(k + 1, i + 1);
                isUsed[i] = false;
            }
        }
    }

}

