package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class boj_2295_세수의합 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.parallelSort(arr);

        ArrayList<Integer> plusList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                plusList.add(arr[i] + arr[j]);
            }
        }

        // 정렬
        Collections.sort(plusList);
        

        // 세수의 합 - 마지막 항을 이분탐색으로 찾기
        int max = Integer.MIN_VALUE;
        for (int i = N-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int t = arr[i] - arr[j];
                if (Collections.binarySearch(plusList, t) >= 0) { // ** 인덱스가 0 이상!!이다. 0도 포함!!ㅠㅠ
                    if (max < arr[i]) {
                        max = arr[i];
                    }
                }
            }
        }
        

        // 정답
        System.out.println(max);
    }
}
