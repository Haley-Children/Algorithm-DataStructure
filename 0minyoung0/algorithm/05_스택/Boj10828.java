// 스택.boj10828;

import java.io.*;
import java.util.*;

public class Boj10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 주어질 명령의 수
		int n = Integer.parseInt(br.readLine());
		// 스택으로 사용할 deque 선언
		Deque<Integer> stack = new ArrayDeque<>();
		
		// n번만큼 명령 실행
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			
			if (oper.equals("push")) {
				int X = Integer.parseInt(st.nextToken());
				stack.offerFirst(X);
			}
			
			else if (oper.equals("pop")) {
				if (stack.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(stack.poll()).append("\n");
				}
			}
			
			else if (oper.equals("size")) {
				sb.append(stack.size()).append("\n");
			}
			
			else if (oper.equals("empty")) {
				if (stack.isEmpty()) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
			}
			
			else { // oper.equals("top")
				if (stack.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
