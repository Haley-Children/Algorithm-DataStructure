package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 수열
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 검사를 위한 배열
        int[] dup = new int[100001];

        int start = 0;
        int end = 0;
        dup[arr[end]] = 1;

        // 최장 길이
        int maxLen = 1;

        while(end != n-1) {
            if(end < n-1) {
                int next = arr[++end];

                System.out.println("next = " + next);
                if(dup[next] >= k) {
                    while (arr[start] != next) {
                        dup[arr[start++]] -= 1;
                        System.out.println(start);
                    }
                    dup[arr[start++]] -= 1;
                }
                maxLen = Math.max(maxLen, end - start + 1);
                dup[next]++;
                System.out.println(dup[next]);
            }
            System.out.println(start + " " + end);
        }

        System.out.println(maxLen);
    }
}
