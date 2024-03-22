package binarysearch.boj;

import java.io.*;
import java.util.*;

public class boj_10816_숫자카드2 {
    static int[] arr;
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.parallelSort(arr);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int cnt = search(target);
            sb.append(cnt).append(" ");
        }
        System.out.println(sb.toString());
    }
    private static int search(int target) {
        return upperBound(target, N) - lowerBound(target, N);
    }
    private static int lowerBound(int target, int len) {
        int st = 0;
        int en = len;

        while (st < en) {
            int mid = (st + en) >>> 1;
            if (arr[mid] >= target) {
                en = mid;
            } else {
                st = mid +1;
            }
        }
        return st;
    }
    private static int upperBound(int target, int len) {
        int st = 0;
        int en = len;

        while (st < en) {
            int mid = (st + en) >>> 1;
            if (arr[mid] > target) {
                en = mid;
            } else {
                st = mid + 1;
            }
        }
        return en;
    }
}
