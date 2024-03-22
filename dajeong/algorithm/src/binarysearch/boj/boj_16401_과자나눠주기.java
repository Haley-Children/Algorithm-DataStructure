package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_16401_과자나눠주기 {
    static int M,N;
    static int[] snacks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 조카 수
        N = Integer.parseInt(st.nextToken()); // 과자 수
        snacks = new int[N];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            max = snacks[i] > max ? snacks[i] : max;
        }


        Arrays.parallelSort(snacks);

        int s = 0;
        int e = max; // 1_000_000_000 대신 max 이용해서 시간 좀 더 줄이기
        while (s < e) {
            int mid = (s + e + 1) >>> 1;
            if (checkCnt(mid)) {
                e = mid - 1;
            } else {
                s = mid;
            }
        }

        System.out.println(s);
        
    }
    private static boolean checkCnt(int x) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (snacks[i] / x);
        }
        return sum < M;
    }

}
