package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1654_랜선자르기 {
    static int K,N, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception{
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st = new StringTokenizer(br.readLine());
     K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 갯수
     N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 갯수
     arr = new int[K];
     for (int i = 0; i < K; i++) {
        arr[i] = Integer.parseInt(br.readLine());
     }

     ps();
     System.out.println(ans);
}

    private static void ps() {
        int st = 1;
        int en = (1<<31)-1;

        while (st < en) {
            int mid = (st + en + 1) >>> 1; // int overflow 방지 (or mid를 long 범위로 하기)
            int cnt = searchCnt(mid);
            if (cnt < N) {
                en = mid - 1;
            } else {
                st = mid;
            }
        }
        ans = st;
    }

    private static int searchCnt(int mid) {
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += (arr[i] / mid);
        }
        return sum;
    }}
