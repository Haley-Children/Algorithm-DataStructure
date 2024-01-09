package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1919_애너그램만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[] arr = new int[27];
        for(char c : str1.toCharArray()) {
            arr[c-'a']++;
        }

        int answer = 0;
        for (char c : str2.toCharArray()) {
            if (arr[c-'a']==0) {
                answer++;
            } else {
                arr[c-'a']--;
            }
        }
        System.out.println(answer*2);
    }

}
