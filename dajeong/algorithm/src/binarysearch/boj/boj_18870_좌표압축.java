package binarysearch.boj;

import java.io.*;
import java.util.*;

public class boj_18870_좌표압축 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[N]; 
        int[] origin = new int[N]; // 정답 찾기 위한 원본 배열
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            origin[i] = arr[i];
        }

        // 정렬
        Arrays.parallelSort(arr);

        // 중복 제거
        /* 
        int[] deleteDuplicatedArr = new int[N];
        int cur = arr[0];
        deleteDuplicatedArr[0] = cur;
        int idx = 1;
        int len = 1; // 중복제거한 배열의 길이
        for (int i = 1; i < N; i++) {
            if (arr[i] == cur) {
                continue;
            } else {
                deleteDuplicatedArr[idx++] = arr[i];
                cur = arr[i];
                len++;
            }
        }
        */
        
        // Stream으로 중복제거 버전
        int[] deleteDuplicatedArr = Arrays.stream(arr).distinct().toArray();
        int len = deleteDuplicatedArr.length;

        // 이분탐색
        for (int i = 0; i < N; i++) {
            int target = origin[i];
            int ans = Arrays.binarySearch(deleteDuplicatedArr, 0, len, target);
            sb.append(ans).append(" ");
        }

        // 정답 출력
        System.out.println(sb.toString());

        
    }
}
