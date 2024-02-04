package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1919_애너그램만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        // 1. 첫번째 문자 돌면서 카운트배열 세기
        int[] arr = new int[27];
        for (char c : str1.toCharArray()) {
            arr[c - 'a']++;
        }

        // 2. 없는 문자일 경우, 더해주기. 있으면 카운트배열 값 -1
        int answer = 0;
        for (char c : str2.toCharArray()) {
            if (arr[c - 'a'] <= 0) {
                answer++;
            } else {
                arr[c - 'a']--;
            }
        }

        // 3. 남는 경우도 더해주기 (카운트 배열에 남은만큼!)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                answer += arr[i];
            }
        }
        System.out.println(answer);
    }

}
