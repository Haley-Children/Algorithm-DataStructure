package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ldx = 0;
        int rdx = 0;
        long sum = arr[ldx];
        int len = Integer.MAX_VALUE;

        search:
        while(ldx < N) {
            // 현재까지의 합이 S 미만이면
            while(sum < S) {
                if(++rdx == N) break search;
                sum += arr[rdx];
            }
            // 현재까지의 합이 S 이상이면
            len = Math.min(len, rdx - ldx + 1);
            sum -= arr[ldx++];
        }
        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }
}
