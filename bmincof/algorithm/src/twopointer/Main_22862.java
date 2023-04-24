package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_22862 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수열의 길이
        int n = Integer.parseInt(st.nextToken());
        // 제거가능한 홀수의 개수
        int k = Integer.parseInt(st.nextToken());

        // 수열
        int[] seq = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터로 최대 길이 재기
        int start = 0;
        int end = 0;

        Queue<Integer> nextStart = new LinkedList<>();

        // 가장 긴 수열의 길이
        int ans = 0;
        // 부분 수열에 포함된 홀수의 개수
        int count = 0;
        while(end != n) {
            while(end < n) {
                int next = seq[end++];

                // 만약 이번에 추가할 숫자가 홀수라면
                if(next % 2 == 1) {
                    count++;
                    nextStart.offer(end);

                    // 삭제할 수 있는 홀수의 개수를 넘으면
                    if(count == k+1) {
                        count = k;
                        start = nextStart.poll();
                        break;
                    }
                }

                ans = Math.max(ans, end - start - count);
            }
        }

        System.out.println(ans);
    }
}
