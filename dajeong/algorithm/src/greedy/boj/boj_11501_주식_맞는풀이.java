package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11501_주식_맞는풀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tk = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= tk; testCase++) {
            int N = Integer.parseInt(br.readLine());
            long[] arr = new long[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            long sum = 0L;
            long maxTarget = arr[N-1];
            for (int i = N-2; i >= 0; i--) {
                if (arr[i] < maxTarget) {
                    sum += (maxTarget - arr[i]);
                } else {
                    maxTarget = arr[i];
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
