package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14921 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 개수
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.parallelSort(values);

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n-1; i++) {
            int fixed = values[i];

            // fixed 와 합치면 0이 될 수 있는 용액의 특성값
            int target = -fixed;
            // 용액들중에서 target 찾기
            int pos = Arrays.binarySearch(values, i+1, n, target);

            // 정확히 0을 만들 수 있는 용액을 찾았다면
            if(pos >= 0) {
                System.out.println(fixed + target);
                return;
            }

            // target 이 들어갈 수 있는 위치를 역산
            pos = -pos-1;

            // 0에 가까운 수를 만들 수 있는 경우에 대해서 탐색
            // target과 가장 가까운 큰 수, target과 가장 가까운 작은 수
            if(pos != n && Math.abs(ans) > Math.abs(fixed + values[pos]))
                ans = fixed + values[pos];

            if(pos-1 > i && Math.abs(ans) > Math.abs(fixed + values[pos-1]))
                ans = fixed + values[pos-1];

        }

        System.out.println(ans);
    }
}
