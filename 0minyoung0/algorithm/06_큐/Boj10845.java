// 큐.boj10845;

import java.io.*;
import java.util.*;

public class Boj10845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		// 정수를 저장하는 큐
		Deque<Integer> queue = new ArrayDeque<>();
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String oper = st.nextToken();
			if (oper.equals("push")) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			else if (oper.equals("pop")) {
				if (queue.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(queue.poll()).append("\n");
				}
			}
			else if (oper.equals("size")) {
				sb.append(queue.size()).append("\n");
			}
			else if (oper.equals("empty")) {
				if (queue.isEmpty()) {
					sb.append("1\n");
				}
				else {
					sb.append("0\n");
				}
			}
			else if (oper.equals("front")) {
				if (queue.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(queue.peek()).append("\n");
				}
			}
			else { // "back"
				if (queue.isEmpty()) {
					sb.append("-1\n");
				}
				else {
					sb.append(queue.peekLast()).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
