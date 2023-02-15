package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_2504 {
    // 1. 스택에 들어가 있는 여는 괄호의 개수를 레벨이라고 정의
    // 2. 레벨마다 점수를 곱하고 더해나감
    // 3. 닫는 괄호를 만나면 현재-1 레벨에 괄호에 맞는 점수 전달
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        // 레벨마다 점수를 저장할 배열
        int[] score = new int[50];
        // 여는 괄호 순서를 저장할 스택
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            // 여는 괄호면 스택에 저장
            if(c == '(' || c == '[') {
                stack.offerLast(c);
                // 닫는 괄호면 점수 계산
            } else {
                int level = stack.size();
                if(!stack.isEmpty() && stack.peekLast() == '(' && c == ')') {
                    // 열자마자 닫힌 괄호면 기본 점수
                    if(score[level] == 0) score[level-1] += 2;
                        // 내부에 다른 괄호들이 있으면 점수 계산
                    else {
                        score[level-1] += score[level] * 2;
                    }
                } else if(!stack.isEmpty() && stack.peekLast() == '[' && c == ']') {
                    // 열자마자 닫힌 괄호면 기본 점수
                    if(score[level] == 0) score[level-1] += 3;
                        // 내부에 다른 괄호들이 있으면 점수 계산
                    else {
                        score[level-1] += score[level] * 3;
                    }
                    // 괄호 쌍이 맞지 않을 경우
                } else {
                    System.out.println(0);
                    return;
                }
                // 짝이 맞는 괄호를 만났으면 기존의 여는 괄호를 제거하고 해당 레벨의 점수 초기화
                stack.pollLast();
                score[level] = 0;
            }
        }
        // 최종 점수 합산한 결과
        System.out.println(score[0]);
    }
}
