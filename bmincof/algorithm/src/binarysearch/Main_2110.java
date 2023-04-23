package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 개수
        int n = Integer.parseInt(st.nextToken());
        // 공유기의 수
        int c = Integer.parseInt(st.nextToken());

        // 집의 좌표
        int[] pos = new int[n];
        for(int i = 0; i < n; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.parallelSort(pos);

        // c개의 공유기를 설치할 최대 간격을 parametric search 로 결정
        int start = 0;
        int end = 1000000000;

        while(start < end) {
            int mid = (start + end + 1) / 2;

            // 간격이 mid일 때 n개의 집에 공유기를 모두 설치할 수 있는지 확인
            // 맨 왼쪽 집부터 설치하면서 시작
            int remain = c-1;
            // 마지막으로 공유기를 설치한 위치
            int prev = pos[0];

            for(int i = 1; i < n; i++) {
                // 이전에 공유기를 설치한 위치와 mid이상 떨어지면 설치하기
                if(pos[i] - prev >= mid) {
                    prev = pos[i];
                    if(--remain == 0) break;
                }
            }

            // 모두 설치해보고 공유기가 남았으면 간격을 줄여보기
            if(remain > 0) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        System.out.println(start);
    }
}
