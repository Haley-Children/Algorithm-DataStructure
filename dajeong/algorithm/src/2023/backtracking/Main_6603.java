package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6603 {

    static int[] arr;
    static boolean[] isUsed;
    static int[] ans;
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            arr = new int[n];
            ans = new int[6];
            isUsed = new boolean[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            recur(0, 0);
            sb.append("\n");

        }
        System.out.println(sb);
    }

    private static void recur(int d, int st) {
        if (d == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = st; i < arr.length; i++) {
            if (isUsed[i]) continue;
            ans[d] = arr[i];
            isUsed[i] = true;
            recur(d+1, i);
            isUsed[i] = false;
        }
    }

}
