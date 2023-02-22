package 스택의활용.boj4949;

import java.io.*;
import java.util.*;

public class Boj4949 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		while (!s.equals(".")) {
			// 스택으로 사용할 덱 선언
			Deque<Character> dq = new ArrayDeque<>();
			// 성공 여부 확인할 boolean 선언
			boolean isPossible = true;
			for (int i=0; i<s.length(); i++) {
				if (s.charAt(i) == '(' || s.charAt(i) == '[') {
					dq.offerFirst(s.charAt(i));
				}
				else if (s.charAt(i) == ')') {
					if (dq.isEmpty() || dq.peek() != '(') {
						isPossible = false;
						break;
					}
					else {
						dq.poll();
					}
				}
				else if (s.charAt(i) == ']') {
					if (dq.isEmpty() || dq.peek() != '[') {
						isPossible = false;
						break;
					}
					else {
						dq.poll();
					}
				}
			}
			if (dq.size() != 0) {
				isPossible = false;
			}
			
			if (isPossible) {
				sb.append("yes\n");
			}
			else {
				sb.append("no\n");
			}
			s = br.readLine();
		}
		System.out.println(sb);
	}
}
