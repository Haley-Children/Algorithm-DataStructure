// 덱.boj10866;

import java.io.*;
import java.util.*;

public class Boj10866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 명령의 수
		int n = Integer.parseInt(br.readLine());
		// 덱 선언
		Deque<Integer> dq = new ArrayDeque<>();
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			if (oper.equals("push_front")) {
				dq.offerFirst(Integer.parseInt(st.nextToken()));
			}
			else if (oper.equals("push_back")) {
				dq.offer(Integer.parseInt(st.nextToken()));
			}
			else if (oper.equals("pop_front")) {
				if (dq.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(dq.poll()).append("\n");
				}
			}
			else if (oper.equals("pop_back")) {
				if (dq.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(dq.pollLast()).append("\n");
				}
			}
			else if (oper.equals("size")) {
				sb.append(dq.size()).append("\n");
			}
			else if (oper.equals("empty")) {
				if (dq.isEmpty()) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
			}
			else if (oper.equals("front")) {
				if (dq.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(dq.peek()).append("\n");
				}
			}
			else { // "back"
				if (dq.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(dq.peekLast()).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
