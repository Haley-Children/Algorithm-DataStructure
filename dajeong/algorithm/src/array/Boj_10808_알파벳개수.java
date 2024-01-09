package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_10808_알파벳개수 {

    public static void main(String[] args) throws IOException {
        int[] cntArr = new int[26];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(char c : str.toCharArray()) {
            cntArr[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cntArr.length; i++) {
            sb.append(cntArr[i]).append(" ");
        }
        System.out.println(sb);
    }

}
