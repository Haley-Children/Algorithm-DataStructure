package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp로 풀지 않으면, O(N*M)이므로 시간 초과가 난다. 이 점을 고려하여 dp로 접근하는 것이 중요.
/*
 * d[i] = d[i-1] + a[i]
 */
public class Main_11659 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] d = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            d[i] = d[i-1] + arr[i];
        }

        for (int k = 1; k <= m; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            sb.append(d[j]-d[i-1]).append("\n");
        }

        System.out.println(sb);
    }

}
