package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1893 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 수
        int T = Integer.parseInt(br.readLine());
        // 패턴찾기를 빠르게 해야하므로 KMP 알고리즘
        while(T-- > 0) {
            // 원문이 될 수 있는 패턴이 단 한 번 나오는 시프트 횟수
            List<Integer> candidates = new ArrayList<>();
            // 알파벳에 맞는 순서를 저장
            Map<Character, Integer> atoi = new HashMap<>();

            // 알파벳 순서
            char[] order = br.readLine().toCharArray();
            for(int i = 0; i < order.length; i++) {
                atoi.put(order[i], i);
            }
            // 평문 (찾아야하는 문자패턴)
            char[] plaintext = br.readLine().toCharArray();
            // 암호문 (패턴을 찾을 문자열)
            char[] ciphertext = br.readLine().toCharArray();

            int pLen = plaintext.length;
            // 한 칸씩 시프트해서 패턴 찾기
            for(int shift = 0; shift < order.length; shift++) {
                // 시프트해서 만들어진 새로운 패턴
                char[] nPT = new char[pLen];
                for(int i = 0; i < pLen; i++) {
                    nPT[i] = order[(atoi.get(plaintext[i]) + shift) % order.length];
                }

                // 찾으려는 패턴의 실패함수
                int[] failure = new int[pLen];

                int j = 0;
                for(int i = 1; i < pLen; i++) {
                    while(j > 0 && nPT[i] != nPT[j])
                        j = failure[j-1];
                    if(nPT[i] == nPT[j])
                        failure[i] = ++j;
                }

                // 패턴 매칭해보기
                int count = 0;
                j = 0;
                for(int i = 0; i < ciphertext.length; i++) {
                    while(j > 0 && ciphertext[i] != nPT[j])
                        j = failure[j-1];
                    if(ciphertext[i] == nPT[j])
                        j++;
                    if(j == failure.length) {
                        count++;
                        if(count > 1) break;
                        j = failure[j-1];
                    }
                }

                // 딱 하나만 일치하면 후보군에 등록
                if(count == 1) {
                    candidates.add(shift);
                }
            }

            // 후보군의 수에 따라 출력
            if(candidates.isEmpty()) {
                sb.append("no solution");
            } else if(candidates.size() == 1) {
                sb.append("unique: ").append(candidates.get(0));
            } else {
                sb.append("ambiguous:");
                for(int cand : candidates) {
                    sb.append(" ").append(cand);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
