package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10804_카드역배치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 1. 카드 초기화
        int[] arr = new int[21];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        // 2. 투포인터로 역순 작업
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            while (start < end) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;

                start++;
                end--;
            }
        }

        // 3. 정답 출력
        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }

    }

}
