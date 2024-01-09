package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13300_방배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 최대 인원 수
        int[][] arr = new int[7][2];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());
            if (arr[grade][gender] == 0) {
                arr[grade][gender]++;
                answer++;
            } else {
                arr[grade][gender]++;
                if (arr[grade][gender] > K) {
                    answer++;
                    arr[grade][gender] = 1;
                }
            }
        }
        System.out.println(answer);
    }

}
