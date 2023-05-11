package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2531 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 접시의 수
        int n = Integer.parseInt(st.nextToken());
        // 초밥 종류 수
        int d = Integer.parseInt(st.nextToken());
        // 연속해서 먹는 접시 수
        int k = Integer.parseInt(st.nextToken());
        // 쿠폰 번호
        int c = Integer.parseInt(st.nextToken());

        // 수열
        int[] dishes = new int[n];

        for(int i = 0; i < n; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 중복 검사를 위한 배열
        int[] eaten = new int[d+1];

        int count = 0;

        int end = 0;
        for(int i = 0; i < k; i++) {
            if(eaten[dishes[end % n]] == 0) count++;
            eaten[dishes[end++]] += 1;
        }

        // 최대 먹은 개수
        int maxDiff = count;

        for(int start = 0; start < n; start++) {
            eaten[dishes[start]]--;
            if(eaten[dishes[start]] == 0) count--;

            if(eaten[dishes[end % n]] == 0) count++;
            eaten[dishes[end++ % n]] += 1;

            if(eaten[c] == 0) {
                maxDiff = Math.max(maxDiff, count + 1);
            } else {
                maxDiff = Math.max(maxDiff, count);
            }
        }

        System.out.println(maxDiff);
    }
}
