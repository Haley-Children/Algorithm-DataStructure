package 스택의활용.boj2504;

import java.io.*;
import java.util.*;

public class Boj2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        // 올바르지 못한 괄호열의 조건
        // 1. 괄호 닫을 때 다른 종류의 여는 괄호가 나오거나 스택이 비어있는 경우
        // 2. 다 끝났는데 스택에 뭐가 남아있는 경우
        
        // 계산 중간값들을 스택에 넣으면 참 좋을텐데.. 괄호는 숫자가 아니잖아..
        // => 그럼 괄호를 숫자로 바꾸면 되잖아
        // '('는 -2로 저장, '['는 -3로 저장
        
        // 스택으로 사용할 덱 선언
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i=0; i<s.length(); i++) {
        	char cur = s.charAt(i);
        	if (cur == '(') {
        		stack.offerFirst(-2);
        	}
        	else if (cur == '[') {
        		stack.offerFirst(-3);
        	}
        	else if (cur == ')') {
        		// temp에 여태 나온 계산 값들을 더해줄거임
        		// 만약에 아무것도 더해지지 않았다면 1로 바꿔줌
        		// 어쨌든 (어쩌구) 꼴이니까 마지막에 2를 곱해줌
        		int temp = 0;
        		
        		if (stack.isEmpty()) {
        			System.out.println(0);
    				return;
        		}
        		
        		while (stack.peek() != -2) {
        			// 올바르지 못한 괄호열
        			if (stack.peek() == -3) {
        				System.out.println(0);
        				return;
        			}
        			temp += stack.poll();
        			
            		if (stack.isEmpty()) {
            			System.out.println(0);
        				return;
            		}
        		}
        		// 이제 스택 맨 위에는 '('이 있음
        		// 더해진 값이 없으면 2를 곱하기 위해서 temp에 1을 넣어줌
        		if (temp == 0) {
        			temp = 1;
        		}
        		// 계산해서 스택에 저장
        		stack.poll();
        		stack.offerFirst(temp * 2);
        	}
        	else { // cur == ']'
        		// 위와 같은 방식
        		// 어쨌든 [어쩌구] 꼴이니까 마지막에 3를 곱해줌
        		int temp = 0;
        		
        		if (stack.isEmpty()) {
        			System.out.println(0);
    				return;
        		}
        		
        		while (stack.peek() != -3) {
        			// 올바르지 못한 괄호열
        			if (stack.peek() == -2) {
        				System.out.println(0);
        				return;
        			}
        			temp += stack.poll();
        			
            		if (stack.isEmpty()) {
            			System.out.println(0);
        				return;
            		}
        		}
        		// 이제 스택 맨 위에는 '['이 있음
        		// 더해진 값이 없으면 3을 곱하기 위해서 temp에 1을 넣어줌
        		if (temp == 0) {
        			temp = 1;
        		}
        		// 계산해서 스택에 저장
        		stack.poll();
        		stack.offerFirst(temp * 3);
        	}
        }
        int ans = 0;
        while (!stack.isEmpty()) {
        	// 다 끝났는데 스택에 괄호가 하나라도 남았으면 올바르지 못한 괄호열
        	if (stack.peek() < 0) {
        		System.out.println(0);
				return;
        	}
        	ans += stack.poll();
        }
        System.out.println(ans);
    }
}