// 스택.boj1874;

import java.io.*;
import java.util.*;

public class Boj1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		int cur = 1;
		
		for (int i=0; i<n; i++) {
			int target = Integer.parseInt(br.readLine());
			while(true) {
				if (stack.isEmpty() || stack.peek() < target) {
					stack.offerFirst(cur++);
					sb.append("+\n");
				}
				else if (stack.peek() == target) {
					stack.poll();
					sb.append("-\n");
					break;
				}
				else {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println(sb);
	}
}
