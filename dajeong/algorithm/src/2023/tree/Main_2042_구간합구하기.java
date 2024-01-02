package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042_구간합구하기 {
    static int N,M,K;
    static long[] input, tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); // 수 갯수
        M = Integer.parseInt(st.nextToken()); // 수 변경이 일어나는 횟수 (update)
        K = Integer.parseInt(st.nextToken()); // 구간의 합 구하는 횟수 (누적합 방식 이용)
        input = new long[N+1]; // 1-indexed
        tree = new long[N+1]; // 펜윅트리 이용 1-indexed

        // input 배열 넣기, 펜윅 트리 배열 update
        for (int i = 1; i <= N; i++) {
            long x = Long.parseLong(br.readLine());
            input[i] = x;
            update(i, x);
        }

        for (int i = 0; i < M + K; i++) {
            st =  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) { // update
                long c = Long.parseLong(st.nextToken());
                update(b,c-input[b]); // 바뀐 값 만큼 update해주어야 하므로 기존값 빼준 diff 구하기
                input[b] = c; // input 갱신
            } else { // 구간합 (누적합 이용) 구하기
                int c = Integer.parseInt(st.nextToken());
                sb.append(intervalSum(b,c)).append("\n");
            }
        }
        System.out.println(sb);

    }

    // 구간합 구하기 (누적합 방식 이용)
    private static long intervalSum(int start, int end) {
        return prefixSum(end)-prefixSum(start-1);
    }

    // 누적합 구하기
    private static long prefixSum(int idx) { // idx까지의 누적합
        long res = 0;
        while(idx>0) {
            res += tree[idx];
            idx -= (idx & (-1)* idx);
        }

        return res;
    }

    private static void update(int idx, long diff) {
        while(idx<=N) {
            tree[idx] += diff;
            idx += (idx & (-1)*idx);
        }
    }
}
