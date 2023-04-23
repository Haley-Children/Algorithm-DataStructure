package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13144 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 수열
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 검사를 위한 배열
        boolean[] dup = new boolean[100001];

        int start = 0;
        int end = 0;
        dup[arr[end]] = true;

        // 경우의 수
        long cnt = 1;

        while(end != n-1) {
            if(end < n-1) {
                int next = arr[++end];

                System.out.println("next = " + next);
                if(dup[next]) {
                    while (arr[start] != next) {
                        dup[arr[start++]] = false;
//                        cnt += end - start;
                    }
                    System.out.println(start);
                    start++;
                }
                cnt += end - start + 1;
                dup[next] = true;
            }
            System.out.println(start + " " + end);
        }

        System.out.println(cnt);
    }

}