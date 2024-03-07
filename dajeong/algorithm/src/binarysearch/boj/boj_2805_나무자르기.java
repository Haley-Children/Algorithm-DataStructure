package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_2805_나무자르기 {
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 나무 수
        M = Integer.parseInt(st.nextToken()); // 상근이가 가져가려고 하는 나무 최소 나무 길이

        int[] treeList = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(st.nextToken());
            treeList[i] = t;
            if (max < t) {
                max = t;
            }
        }

        // tree들을 정렬할 필요가 없구나! 이분탐색이라고 해서 무조건 정렬하는거 아닌데 습관적으로 정렬해버렸다. 해당 문제에서는 정렬이 필요없다.

        int start = 0;
        int end = max;
        while (start < end) {
            int mid = (start + end + 1) >>> 1;
            long sum = 0; // sum이 long 범위일 수 있음 주의!!!
            for (int i = 0; i < N; i++) {
                sum += treeList[i] > mid ? treeList[i] - mid : 0;
            }
            if (sum < M) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        System.out.println(start);
    }

}
