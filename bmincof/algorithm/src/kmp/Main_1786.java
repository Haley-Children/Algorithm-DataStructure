package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1786 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();

        int[] failure = new int[pattern.length];

        // 실패함수 만들기
        int j = 0;
        for(int i = 1; i < pattern.length; i++) {
            while(j > 0 && pattern[i] != pattern[j]) j = failure[j-1];
            if(pattern[i] == pattern[j]) failure[i] = ++j;
        }

        int cnt = 0;
        List<Integer> matchedIdx = new ArrayList<>();
        // 문자열에서 패턴 찾기
        j = 0;
        for(int i = 0; i < str.length; i++) {
            while(j > 0 && str[i] != pattern[j]) j = failure[j-1];
            if(str[i] == pattern[j]) j++;
            // 문자열이 일치하면
            if(j == failure.length) {
                cnt++;
                matchedIdx.add(i-j+2);
                j = failure[j-1];
            }
        }

        StringBuilder sb = new StringBuilder().append(cnt).append("\n");

        for(int idx : matchedIdx) {
            sb.append(idx).append(" ");
        }

        System.out.println(sb);
    }
}
