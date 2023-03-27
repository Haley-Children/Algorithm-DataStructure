package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_2295 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 데이터 개수
        int n = Integer.parseInt(br.readLine());

        // 집합 U 입력받기
        int[] arr = new int[n];
        // 집합 U의 원소 2개를 골라서 만들 수 있는 합
        List<Integer> sumOfTwo = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // U를 오름차순으로 정렬
        Arrays.parallelSort(arr);

        // U의 원소 2개를 골라서 만들 수 있는 합 계산
        // x,y,z,k가 서로 같아도 되므로 중복해서 사용 가능
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                sumOfTwo.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(sumOfTwo);

        for(int i = n-1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                // 큰 값 - 작은 값이 sumOfTwo에 있다면 수 세 개를 더해서 만들 수 있는 가장 큰 수는 큰 값
                if(Collections.binarySearch(sumOfTwo, arr[i] - arr[j]) > -1) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}
